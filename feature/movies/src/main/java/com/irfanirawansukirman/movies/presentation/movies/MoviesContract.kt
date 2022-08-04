package com.irfanirawansukirman.movies.presentation.movies

import com.irfanirawansukirman.core.UiEffect
import com.irfanirawansukirman.core.UiEvent
import com.irfanirawansukirman.core.UiState
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.presentation.movies.model.MoviesUiModel

object MoviesContract {

    sealed class MoviesEvent : UiEvent {
        object OnGetRemoteMoviesPopular : MoviesEvent()
        data class OnInsertCacheMoviePopular(val moviesDataModel: MoviesDataModel) : MoviesEvent()
    }

    sealed class MoviesState {
        object Idle : MoviesState()
        object Loading : MoviesState()
        data class SuccessRemoteGetMoviesPopular(val movies: List<MoviesUiModel>) : MoviesState()
        data class SuccessCacheInsertMoviePopular(val message: String) : MoviesState()
    }

    sealed class MoviesEffect : UiEffect {
        data class ShowError(val message: String) : MoviesEffect()
    }

    data class State(val moviesState: MoviesState) : UiState
}