package com.example.learningmviarchitecture.feature.favorites

import androidx.lifecycle.viewModelScope
import com.example.learningmviarchitecture.base.BaseViewModel
import com.example.learningmviarchitecture.data.repository.AppRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val appRepositoryImpl: AppRepositoryImpl
) : BaseViewModel<FavoritesContract.FavoritesEvent, FavoritesContract.State, FavoritesContract.FavoritesEffect>() {

    override fun createInitialState(): FavoritesContract.State {
        return FavoritesContract.State(FavoritesContract.FavoritesState.Idle)
    }

    override fun handleEvent(event: FavoritesContract.FavoritesEvent) {
        when (event) {
            is FavoritesContract.FavoritesEvent.OnGetCacheMoviesPopular -> getCacheMoviesPopular()
        }
    }

    private fun getCacheMoviesPopular() {
        viewModelScope.launch {
            setState { copy(state = FavoritesContract.FavoritesState.Loading) }

            try {
                delay(1_000)

                val data = appRepositoryImpl.getCacheMoviesPopular()

                withContext(Dispatchers.Main) {
                    setState { copy(state = FavoritesContract.FavoritesState.Success(data)) }
                }
            } catch (e: Exception) {
                setEffect { FavoritesContract.FavoritesEffect.ShowToast }
            }
        }
    }
}