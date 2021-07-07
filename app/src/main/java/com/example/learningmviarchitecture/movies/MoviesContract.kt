package com.example.learningmviarchitecture.movies

import com.example.learningmviarchitecture.data.model.Result
import com.example.learningmviarchitecture.util.ui.UIEffect
import com.example.learningmviarchitecture.util.ui.UiEvent
import com.example.learningmviarchitecture.util.ui.UiState

class MoviesContract {

    sealed class MoviesEvent : UiEvent {
        object OnGetMoviesPopular : MoviesEvent()
    }

    sealed class MoviesState {
        object Idle : MoviesState()
        object Loading : MoviesState()
        data class Success(val data: List<Result>?) : MoviesState()
    }

    sealed class MoviesEffect : UIEffect {
        object ShowToast : MoviesEffect()
    }

    data class State(val state: MoviesState) : UiState
}
