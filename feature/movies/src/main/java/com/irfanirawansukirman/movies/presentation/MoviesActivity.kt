package com.irfanirawansukirman.movies.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.irfanirawansukirman.core.ViewModelFactory
import com.irfanirawansukirman.movies.R
import com.irfanirawansukirman.movies.databinding.MoviesActivityBinding
import com.irfanirawansukirman.movies.di.MoviesComponentProvider
import com.irfanirawansukirman.remote.data.response.MoviesPopularData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@ExperimentalCoroutinesApi
class MoviesActivity : AppCompatActivity() {

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
        val id = item.itemId

        if (id == R.id.action_about) {
            navigateToAbout()
            return true
        }

        return super.onOptionsItemSelected(item)
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
            adapter = MoviesAdapter()
        }
    }

    private fun initObservers() {
        // collect ui state
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.moviesState) {
                    is MoviesContract.MoviesState.Idle -> binding.progress.isVisible = false
                    is MoviesContract.MoviesState.Loading -> binding.progress.isVisible = true
                    is MoviesContract.MoviesState.Success -> {
                        binding.apply {
                            progress.isVisible = false
                            recyclerMovies.isVisible = true

                            showMovies(it.moviesState.movies)
                        }
                    }
                }
            }
        }

        // collect side effects
        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                when (it) {
                    is MoviesContract.MoviesEffect.ShowError -> binding.progress.isVisible = false
                }
            }
        }
    }

    private fun showMovies(movies: List<MoviesPopularData>) {
        adapter.addAllMovies(movies)
    }
}