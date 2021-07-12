package com.example.learningmviarchitecture.feature.favorites

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import com.example.learningmviarchitecture.databinding.ActivityFavoritesBinding
import com.example.learningmviarchitecture.di.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FavoritesActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: FavoritesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObservers()
        initAdapter()

        binding.recyclerFavorites.adapter = adapter

        getFavorites()
    }

    private fun getFavorites() {
        viewModel.setEvent(FavoritesContract.FavoritesEvent.OnGetCacheMoviesPopular)
    }

    private fun initAdapter() {
        if (!::adapter.isInitialized) {
            adapter = FavoritesAdapter()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    private fun initObservers() {
        // collect ui state
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.state) {
                    is FavoritesContract.FavoritesState.Idle -> binding.progress.isVisible = false
                    is FavoritesContract.FavoritesState.Loading -> binding.progress.isVisible = true
                    is FavoritesContract.FavoritesState.Success -> {
                        binding.apply {
                            progress.isVisible = false
                            recyclerFavorites.isVisible = true

                            showFavorites(it.state.data)
                        }
                    }
                }
            }
        }

        // collect side effects
        lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect {
                when (it) {
                    is FavoritesContract.FavoritesEffect.ShowToast -> binding.progress.isVisible =
                        false
                }
            }
        }
    }

    private fun showFavorites(data: List<MoviePopularEnt>?) {
        adapter.addAllMovies(data ?: emptyList())
    }
}