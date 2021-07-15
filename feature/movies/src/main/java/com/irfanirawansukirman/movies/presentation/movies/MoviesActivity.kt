package com.irfanirawansukirman.movies.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.core.ViewModelFactory
import com.irfanirawansukirman.movies.R
import com.irfanirawansukirman.movies.databinding.MoviesActivityBinding
import com.irfanirawansukirman.movies.di.MoviesComponentProvider
import com.irfanirawansukirman.movies.presentation.favorite.FavoriteActivity
import com.irfanirawansukirman.remote.data.response.MoviesPopularData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
class MoviesActivity : AppCompatActivity(), MoviesListener {

    private lateinit var binding: MoviesActivityBinding
    private lateinit var adapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MoviesViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()

        initBinding()
        setContentView(binding.root)

        initAdapter()
        initObservers()

        binding.recyclerMovies.adapter = adapter

        getRemoteMoviesPopular()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.movies, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> navigateToAbout()
            else -> navigateToFavorite()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigateToFavorite() {
        startActivity(Intent(this, FavoriteActivity::class.java))
    }

    private fun navigateToAbout() {
        try {
            val intent = Intent(
                this,
                Class.forName("com.irfanirawansukirman.about.AboutActivity")
            )
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun getRemoteMoviesPopular() {
        if (viewModel.currentState.moviesState is MoviesContract.MoviesState.Idle) {
            viewModel.setEvent(MoviesContract.MoviesEvent.OnGetRemoteMoviesPopular)
        }
    }

    private fun initDependencies() {
        (application as MoviesComponentProvider)
            .getMoviesComponent()
            .inject(this)
    }

    private fun initBinding() {
        if (!::binding.isInitialized) {
            binding = MoviesActivityBinding.inflate(layoutInflater)
        }
    }

    private fun initAdapter() {
        if (!::adapter.isInitialized) {
            adapter = MoviesAdapter(this)
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.moviesState) {
                    is MoviesContract.MoviesState.Idle -> hideProgress()
                    is MoviesContract.MoviesState.Loading -> showProgress()
                    is MoviesContract.MoviesState.SuccessRemoteGetMoviesPopular -> {
                        hideProgress()
                        showMovies(it.moviesState.movies)
                    }
                    is MoviesContract.MoviesState.SuccessCacheInsertMoviePopular -> {
                        hideProgress()
                        showToast(it.moviesState.message)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is MoviesContract.MoviesEffect.ShowError -> hideProgress()
                }
            }
        }
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

    private fun showToast(message: String) {
        val result = filterMessage(message)
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    private fun filterMessage(message: String): String {
        return if (message == "Success") {
            "Movie saved successfully"
        } else {
            "Movie failed to save"
        }
    }

    private fun showMovies(movies: List<MoviesPopularData>) {
        binding.recyclerMovies.isVisible = true

        adapter.addAllMovies(movies)
    }

    override fun onClickMovie(moviesPopularEnt: MoviesPopularEnt) {
        insertCacheMoviePopular(moviesPopularEnt)
    }

    private fun insertCacheMoviePopular(moviesPopularEnt: MoviesPopularEnt) {
        viewModel.setEvent(MoviesContract.MoviesEvent.OnSaveCacheMoviePopular(moviesPopularEnt))
    }
}