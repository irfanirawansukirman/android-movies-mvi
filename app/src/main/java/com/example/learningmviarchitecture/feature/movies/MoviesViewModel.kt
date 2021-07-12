package com.example.learningmviarchitecture.feature.movies

import androidx.lifecycle.viewModelScope
import com.example.learningmviarchitecture.base.BaseViewModel
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import com.example.learningmviarchitecture.data.repository.AppRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) : BaseViewModel<MoviesContract.MoviesEvent, MoviesContract.State, MoviesContract.MoviesEffect>() {

    override fun createInitialState(): MoviesContract.State {
        return MoviesContract.State(MoviesContract.MoviesState.Idle)
    }

    override fun handleEvent(event: MoviesContract.MoviesEvent) {
        when (event) {
            is MoviesContract.MoviesEvent.OnGetMoviesPopular -> getRemoteMoviesPopular()
            is MoviesContract.MoviesEvent.OnCacheInsertMoviePopular -> insertCacheMoviePopular(event.moviePopularEnt)
        }
    }

    private fun getRemoteMoviesPopular() {
        viewModelScope.launch {
            setState { copy(state = MoviesContract.MoviesState.Loading) }

            try {
                delay(1_000)

                val data = appRepositoryImpl.getRemoteMoviesPopular()

                withContext(Dispatchers.Main) {
                    setState { copy(state = MoviesContract.MoviesState.Success(data)) }
                }
            } catch (e: Exception) {
                setEffect { MoviesContract.MoviesEffect.ShowToast }
            }
        }
    }

    private fun insertCacheMoviePopular(moviePopularEnt: MoviePopularEnt) {
        viewModelScope.launch {
            try {
                appRepositoryImpl.insertCacheMoviePopular(moviePopularEnt)
            } catch (e: Exception) {
                setEffect { MoviesContract.MoviesEffect.ShowToast }
            }
        }
    }
}
