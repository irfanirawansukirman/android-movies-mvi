package com.irfanirawansukirman.movies.presentation.favorite

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.core.UiEffect
import com.irfanirawansukirman.core.UiEvent
import com.irfanirawansukirman.core.UiState

object FavoriteContract {

    sealed class FavoriteEvent : UiEvent {
        object OnGetCacheMoviesPopular : FavoriteEvent()
    }

    sealed class FavoriteState {
        object Idle : FavoriteState()
        object Loading : FavoriteState()
        data class SuccessCacheGetMoviesPopular(val movies: List<MoviesPopularEnt>) :
            FavoriteState()
    }

    sealed class FavoriteEffect : UiEffect {
        data class ShowError(val message: String) : FavoriteEffect()
    }

    data class State(val favoriteState: FavoriteState) : UiState
}