package com.irfanirawansukirman.movies.presentation.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.irfanirawansukirman.core.ViewModelFactory
import com.irfanirawansukirman.movies.databinding.FavoriteActivityBinding
import com.irfanirawansukirman.movies.di.MoviesComponentProvider
import com.irfanirawansukirman.movies.presentation.movies.model.MoviesUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: FavoriteActivityBinding
    private lateinit var adapter: FavoriteAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<FavoriteViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()

        initBinding()
        setContentView(binding.root)

        showBackButton()

        initAdapter()
        initObservers()

        binding.recyclerFavorite.adapter = adapter

        getCacheMoviesPopular()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()

        return super.onOptionsItemSelected(item)
    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getCacheMoviesPopular() {
        if (viewModel.currentState.favoriteState is FavoriteContract.FavoriteState.Idle) {
            viewModel.setEvent(FavoriteContract.FavoriteEvent.OnGetCacheMoviesPopular)
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.favoriteState) {
                    is FavoriteContract.FavoriteState.Idle -> hideProgress()
                    is FavoriteContract.FavoriteState.Loading -> showProgress()
                    is FavoriteContract.FavoriteState.SuccessCacheGetMoviesPopular -> {
                        hideProgress()
                        showMovies(it.favoriteState.movies)
                    }
                }
            }
        }
    }

    private fun showMovies(movies: List<MoviesUiModel>) {
        binding.recyclerFavorite.isVisible = true

        adapter.addAllMovies(movies)
    }

    private fun initAdapter() {
        if (!::adapter.isInitialized) {
            adapter = FavoriteAdapter()
        }
    }

    private fun initBinding() {
        if (!::binding.isInitialized) {
            binding = FavoriteActivityBinding.inflate(layoutInflater)
        }
    }

    private fun initDependencies() {
        (application as MoviesComponentProvider)
            .getMoviesComponent()
            .inject(this)
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }
}