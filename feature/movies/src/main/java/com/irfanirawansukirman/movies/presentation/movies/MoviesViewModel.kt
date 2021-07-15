package com.irfanirawansukirman.movies.presentation.movies

import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.core.base.BaseViewModel
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.domain.MoviesUseCaseImpl
import com.irfanirawansukirman.movies.presentation.movies.mapper.MoviesDomainUiMapper
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesUseCaseImpl: MoviesUseCaseImpl,
    private val moviesDomainUiMapper: MoviesDomainUiMapper
) : BaseViewModel<MoviesContract.MoviesEvent, MoviesContract.State, MoviesContract.MoviesEffect>() {

    override fun createInitialState(): MoviesContract.State {
        return MoviesContract.State(MoviesContract.MoviesState.Idle)
    }

    override fun handleEvent(event: MoviesContract.MoviesEvent) {
        when (event) {
            is MoviesContract.MoviesEvent.OnGetRemoteMoviesPopular -> getRemoteMoviesPopular()
            is MoviesContract.MoviesEvent.OnSaveCacheMoviePopular -> insertCacheMoviePopular(event.moviesDataModel)
        }
    }

    private fun insertCacheMoviePopular(moviesDataModel: MoviesDataModel) {
        viewModelScope.launch {
            moviesUseCaseImpl.insertMoviePopular(moviesDataModel)
                .onStart { emit(Resource.Loading); delay(1_000) }
                .collect {
                    when (it) {
                        is Resource.Loading -> setState { copy(moviesState = MoviesContract.MoviesState.Loading) }
                        is Resource.Empty -> setState { copy(moviesState = MoviesContract.MoviesState.Idle) }
                        is Resource.Success -> {
                            val message = it.data ?: "Success"
                            setState {
                                copy(
                                    moviesState = MoviesContract.MoviesState.SuccessCacheInsertMoviePopular(
                                        message
                                    )
                                )
                            }
                        }
                        is Resource.Error -> setEffect {
                            MoviesContract.MoviesEffect.ShowError(
                                message = it.exception.message ?: "Error"
                            )
                        }
                    }
                }
        }
    }

    private fun getRemoteMoviesPopular() {
        viewModelScope.launch {
            moviesUseCaseImpl.getRemoteMoviesPopular()
                .onStart { emit(Resource.Loading); delay(1_000) }
                .collect {
                    when (it) {
                        is Resource.Loading -> setState { copy(moviesState = MoviesContract.MoviesState.Loading) }
                        is Resource.Empty -> setState { copy(moviesState = MoviesContract.MoviesState.Idle) }
                        is Resource.Success -> {
                            val movies = it.data ?: emptyList()
                            setState {
                                copy(
                                    moviesState = MoviesContract.MoviesState.SuccessRemoteGetMoviesPopular(
                                        moviesDomainUiMapper.fromList(movies)
                                    )
                                )
                            }
                        }
                        is Resource.Error -> setEffect {
                            MoviesContract.MoviesEffect.ShowError(
                                message = it.exception.message ?: "Error"
                            )
                        }
                    }
                }
        }
    }
}