package com.irfanirawansukirman.movies.di

import com.irfanirawansukirman.movies.data.cache.mapper.MoviesCacheMapper
import com.irfanirawansukirman.movies.data.mapper.MoviesDomainDataMapper
import com.irfanirawansukirman.movies.data.remote.mapper.MoviesNetworkMapper
import com.irfanirawansukirman.movies.presentation.movies.mapper.MoviesDomainUiMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesMapperModule {

    @Singleton
    @Provides
    fun provideMoviesPopularMapper() = MoviesNetworkMapper()

    @Singleton
    @Provides
    fun provideMoviesCacheMapper() = MoviesCacheMapper()

    @Singleton
    @Provides
    fun provideMoviesDataDomainMapper() = MoviesDomainDataMapper()

    @Singleton
    @Provides
    fun provideMoviesDomainUiMapper() = MoviesDomainUiMapper()
}