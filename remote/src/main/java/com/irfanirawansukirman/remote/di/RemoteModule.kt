package com.irfanirawansukirman.remote.di

import android.app.Application
import com.irfanirawansukirman.remote.BuildConfig
import com.irfanirawansukirman.remote.factory.ApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideMovieRetrofit(application: Application): Retrofit =
        ApiFactory.build(
            application,
            BuildConfig.MOVIE_API_BASE_URL
        )
}