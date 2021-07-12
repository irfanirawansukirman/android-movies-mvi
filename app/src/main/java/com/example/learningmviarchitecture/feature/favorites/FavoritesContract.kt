package com.example.learningmviarchitecture.feature.favorites

import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import com.example.learningmviarchitecture.util.ui.UIEffect
import com.example.learningmviarchitecture.util.ui.UiEvent
import com.example.learningmviarchitecture.util.ui.UiState

class FavoritesContract {

    sealed class FavoritesEvent() : UiEvent {
        object OnGetCacheMoviesPopular : FavoritesEvent()
    }

    sealed class FavoritesState {
        object Idle : FavoritesState()
        object Loading : FavoritesState()
        data class Success(val data: List<MoviePopularEnt>?) : FavoritesState()
    }

    sealed class FavoritesEffect : UIEffect {
        object ShowToast : FavoritesEffect()
    }

    data class State(val state: FavoritesState) : UiState
}