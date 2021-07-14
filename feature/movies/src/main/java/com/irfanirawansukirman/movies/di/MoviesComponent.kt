package com.irfanirawansukirman.movies.di

import android.app.Application
import com.irfanirawansukirman.movies.presentation.MoviesActivity
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [MoviesModule::class, MoviesVMModule::class])
interface MoviesComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MoviesComponent
    }

    fun inject(activity: MoviesActivity)
}