package com.example.learningmviarchitecture.feature.movies

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import com.example.learningmviarchitecture.data.remote.model.Result
import com.example.learningmviarchitecture.databinding.ActivityMoviesBinding
import com.example.learningmviarchitecture.di.ViewModelFactory
import com.example.learningmviarchitecture.feature.favorites.FavoritesActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MoviesActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObservers()
        initAdapter()

        binding.apply {
            recyclerMovies.adapter = adapter

            tvFavorites.setOnClickListener {
                startActivity(
                    Intent(
                        this@MoviesActivity,
                        FavoritesActivity::class.java
                    )
                )
            }
        }

        getMoviesPopular()
    }

    private fun initViewModel() {
        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)
        }
    }

    private fun initAdapter() {
        if (!::adapter.isInitialized) {
            adapter = MoviesAdapter {
                val entity = MoviePopularEnt(
                    it.id,
                    it.originalTitle,
                    it.posterPath,
                    it.releaseDate,
                    it.overview,
                    ""
                )
                insertCacheMoviePopular(entity)
            }
        }
    }

    private fun getMoviesPopular() {
        viewModel.setEvent(MoviesContract.MoviesEvent.OnGetMoviesPopular)
    }

    private fun insertCacheMoviePopular(moviePopularEnt: MoviePopularEnt) {
        viewModel.setEvent(MoviesContract.MoviesEvent.OnCacheInsertMoviePopular(moviePopularEnt))
    }

    private fun initObservers() {
        // collect ui state
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.state) {
                    is MoviesContract.MoviesState.Idle -> binding.progress.isVisible = false
                    is MoviesContract.MoviesState.Loading -> binding.progress.isVisible = true
                    is MoviesContract.MoviesState.Success -> {
                        binding.apply {
                            progress.isVisible = false
                            recyclerMovies.isVisible = true

                            showMovies(it.state.data)
                        }
                    }
                }
            }
        }

        // collect side effects
        lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect {
                when (it) {
                    is MoviesContract.MoviesEffect.ShowToast -> binding.progress.isVisible = false
                }
            }
        }
    }

    private fun showMovies(data: List<Result>?) {
        adapter.addAllMovies(data ?: emptyList())
    }
}