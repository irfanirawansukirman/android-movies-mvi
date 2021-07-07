package com.example.learningmviarchitecture.di

import com.example.learningmviarchitecture.data.repository.MoviesRepository
import com.example.learningmviarchitecture.data.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}