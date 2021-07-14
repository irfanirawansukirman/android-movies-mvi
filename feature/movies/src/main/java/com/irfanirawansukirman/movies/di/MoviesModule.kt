package com.irfanirawansukirman.movies.di

import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.cache.di.CacheModule
import com.irfanirawansukirman.core.base.BaseModule
import com.irfanirawansukirman.movies.data.MoviesAppRepositoryImpl
import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepositoryImpl
import com.irfanirawansukirman.movies.data.remote.MoviesRemoteRepositoryImpl
import com.irfanirawansukirman.movies.domain.MoviesUseCaseImpl
import com.irfanirawansukirman.remote.data.service.MovieService
import com.irfanirawansukirman.remote.di.RemoteModule
import com.irfanirawansukirman.remote.factory.ApiFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [BaseModule::class, RemoteModule::class, CacheModule::class])
class MoviesModule {

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = ApiFactory.getService(retrofit)

    @Singleton
    @Provides
    fun provideMoviesRemoteRepositoryImpl(movieService: MovieService): MoviesRemoteRepositoryImpl {
        return MoviesRemoteRepositoryImpl(movieService)
    }

    @Singleton
    @Provides
    fun provideMoviesCacheRepositoryImpl(movieDao: MovieDao): MoviesCacheRepositoryImpl {
        return MoviesCacheRepositoryImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideMoviesAppRepositoryImpl(
        moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl,
        moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl
    ): MoviesAppRepositoryImpl {
        return MoviesAppRepositoryImpl(moviesRemoteRepositoryImpl, moviesCacheRepositoryImpl)
    }

    @Singleton
    @Provides
    fun provideMoviesUseCaseImpl(moviesAppRepositoryImpl: MoviesAppRepositoryImpl): MoviesUseCaseImpl {
        return MoviesUseCaseImpl(moviesAppRepositoryImpl)
    }
}