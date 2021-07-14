package com.irfanirawansukirman.movies.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.core.base.BaseViewModel
import com.irfanirawansukirman.movies.domain.MoviesUseCaseImpl
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesUseCaseImpl: MoviesUseCaseImpl
) : BaseViewModel<MoviesContract.MoviesEvent, MoviesContract.State, MoviesContract.MoviesEffect>() {

    override fun createInitialState(): MoviesContract.State {
        return MoviesContract.State(MoviesContract.MoviesState.Idle)
    }

    override fun handleEvent(event: MoviesContract.MoviesEvent) {
        when (event) {
            is MoviesContract.MoviesEvent.OnGetRemoteMoviesPopular -> getRemoteMoviesPopular()
        }
    }

    private fun getRemoteMoviesPopular() {
        viewModelScope.launch {
            moviesUseCaseImpl.getRemoteMoviesPopular()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> setState { copy(moviesState = MoviesContract.MoviesState.Loading) }
                        is Resource.Empty -> setState { copy(moviesState = MoviesContract.MoviesState.Idle) }
                        is Resource.Success -> {
                            val movies = it.data.results ?: emptyList()
                            Log.d("Irfan", movies[0].originalTitle ?: "Title")
                            setState { copy(moviesState = MoviesContract.MoviesState.Success(movies)) }
                        }
                        is Resource.Error -> setEffect {
                            MoviesContract.MoviesEffect.ShowError(
                                it.exception.message ?: "Error"
                            )
                        }
                    }
                }
        }
    }
}