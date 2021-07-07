package com.example.learningmviarchitecture.movies

import androidx.lifecycle.viewModelScope
import com.example.learningmviarchitecture.base.BaseViewModel
import com.example.learningmviarchitecture.data.network.ApiClient
import com.example.learningmviarchitecture.data.repository.MoviesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepositoryImpl: MoviesRepositoryImpl
) : BaseViewModel<MoviesContract.MoviesEvent, MoviesContract.State, MoviesContract.MoviesEffect>() {

    override fun createInitialState(): MoviesContract.State {
        return MoviesContract.State(MoviesContract.MoviesState.Idle)
    }

    override fun handleEvent(event: MoviesContract.MoviesEvent) {
        when (event) {
            is MoviesContract.MoviesEvent.OnGetMoviesPopular -> getMoviesPopular()
        }
    }

    private fun getMoviesPopular() {
        viewModelScope.launch {
            setState { copy(state = MoviesContract.MoviesState.Loading) }

            try {
                delay(1_000)

                val data = moviesRepositoryImpl.getMoviesPopular()

                withContext(Dispatchers.Main) {
                    setState { copy(state = MoviesContract.MoviesState.Success(data)) }
                }
            } catch (e: Exception) {
                setEffect { MoviesContract.MoviesEffect.ShowToast }
            }
        }
    }
}
