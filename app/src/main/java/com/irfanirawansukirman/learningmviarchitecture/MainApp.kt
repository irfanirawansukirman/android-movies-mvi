package com.irfanirawansukirman.learningmviarchitecture

import android.app.Application
import com.irfanirawansukirman.movies.di.DaggerMoviesComponent
import com.irfanirawansukirman.movies.di.MoviesComponent
import com.irfanirawansukirman.movies.di.MoviesComponentProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainApp : Application(), MoviesComponentProvider {

    override fun getMoviesComponent(): MoviesComponent {
        return DaggerMoviesComponent
            .builder()
            .application(this)
            .build()
    }
}