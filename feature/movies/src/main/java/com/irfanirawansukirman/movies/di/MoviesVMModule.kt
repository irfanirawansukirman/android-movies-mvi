package com.irfanirawansukirman.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irfanirawansukirman.core.ViewModelFactory
import com.irfanirawansukirman.core.ViewModelKey
import com.irfanirawansukirman.movies.presentation.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
abstract class MoviesVMModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}