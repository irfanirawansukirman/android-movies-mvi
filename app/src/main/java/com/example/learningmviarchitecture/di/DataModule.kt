package com.example.learningmviarchitecture.di

import com.example.learningmviarchitecture.data.cache.repository.MoviesCacheRepositoryImpl
import com.example.learningmviarchitecture.data.remote.repository.MoviesRemoteRepositoryImpl
import com.example.learningmviarchitecture.data.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, CacheModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl,
        moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl
    ): AppRepositoryImpl {
        return AppRepositoryImpl(moviesRemoteRepositoryImpl, moviesCacheRepositoryImpl)
    }
}