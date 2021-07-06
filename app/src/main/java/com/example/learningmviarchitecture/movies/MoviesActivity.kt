package com.example.learningmviarchitecture.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.learningmviarchitecture.R
import com.example.learningmviarchitecture.databinding.ActivityMoviesBinding
import kotlinx.coroutines.flow.collect

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        binding.btnSubmit.setOnClickListener { clear();getMoviesPopular() }
    }

    private fun clear() {
        binding.apply {
            tvTitle.text = null
            ivPoster.load("")
        }
    }

    private fun getMoviesPopular() {
        viewModel.setEvent(MoviesContract.MoviesEvent.OnGetMoviesPopular)
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
                            val number = (0..9).random()
                            val movie = it.state.data[number]
                            tvTitle.text = movie.originalTitle
                            ivPoster.load("https://image.tmdb.org/t/p/w500${movie.backdropPath}") {
                                crossfade(true)
                                placeholder(R.drawable.ic_launcher_background)
                                transformations(CircleCropTransformation())
                            }
                        }
                    }
                }
            }
        }

        // collect side effects
        lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect {
                when (it) {
                    is MoviesContract.MoviesEffect.ShowToast -> {
                        binding.progress.isVisible = false

                        // show a error message
                        showToast("Error, number is even")
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}