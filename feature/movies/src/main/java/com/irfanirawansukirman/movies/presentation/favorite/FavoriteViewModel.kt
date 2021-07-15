package com.irfanirawansukirman.movies.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.core.base.BaseViewModel
import com.irfanirawansukirman.movies.domain.MoviesUseCaseImpl
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val moviesUseCaseImpl: MoviesUseCaseImpl
) : BaseViewModel<FavoriteContract.FavoriteEvent, FavoriteContract.State, FavoriteContract.FavoriteEffect>() {

    override fun createInitialState(): FavoriteContract.State {
        return FavoriteContract.State(FavoriteContract.FavoriteState.Idle)
    }

    override fun handleEvent(event: FavoriteContract.FavoriteEvent) {
        when (event) {
            is FavoriteContract.FavoriteEvent.OnGetCacheMoviesPopular -> getCacheMoviesPopular()
        }
    }

    private fun getCacheMoviesPopular() {
        viewModelScope.launch {
            moviesUseCaseImpl.getCacheMoviesPopular()
                .onStart { emit(Resource.Loading); delay(1_000) }
                .collect {
                    when (it) {
                        is Resource.Empty -> setState { copy(favoriteState = FavoriteContract.FavoriteState.Idle) }
                        is Resource.Loading -> setState { copy(favoriteState = FavoriteContract.FavoriteState.Loading) }
                        is Resource.Success -> {
                            val movies = it.data ?: emptyList()
                            setState {
                                copy(
                                    favoriteState = FavoriteContract.FavoriteState.SuccessCacheGetMoviesPopular(
                                        movies
                                    )
                                )
                            }
                        }
                        is Resource.Error -> setEffect {
                            FavoriteContract.FavoriteEffect.ShowError(
                                message = it.exception.message ?: "Error"
                            )
                        }
                    }
                }
        }
    }
}