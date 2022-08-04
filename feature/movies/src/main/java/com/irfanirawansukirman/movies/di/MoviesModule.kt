package com.irfanirawansukirman.movies.di

import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.cache.di.CacheModule
import com.irfanirawansukirman.core.base.BaseModule
import com.irfanirawansukirman.movies.data.MoviesAppRepositoryImpl
import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepositoryImpl
import com.irfanirawansukirman.movies.data.cache.mapper.MoviesCacheMapper
import com.irfanirawansukirman.movies.data.mapper.MoviesDomainDataMapper
import com.irfanirawansukirman.movies.data.remote.repository.MoviesRemoteRepositoryImpl
import com.irfanirawansukirman.movies.data.remote.mapper.MoviesNetworkMapper
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
    fun provideMoviesRemoteRepositoryImpl(
        movieService: MovieService,
        moviesNetworkMapper: MoviesNetworkMapper
    ): MoviesRemoteRepositoryImpl {
        return MoviesRemoteRepositoryImpl(movieService, moviesNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideMoviesCacheRepositoryImpl(
        movieDao: MovieDao,
        moviesCacheMapper: MoviesCacheMapper
    ): MoviesCacheRepositoryImpl {
        return MoviesCacheRepositoryImpl(movieDao, moviesCacheMapper)
    }

    @Singleton
    @Provides
    fun provideMoviesAppRepositoryImpl(
        moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl,
        moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl,
        moviesDomainDataMapper: MoviesDomainDataMapper
    ): MoviesAppRepositoryImpl {
        return MoviesAppRepositoryImpl(
            moviesRemoteRepositoryImpl,
            moviesCacheRepositoryImpl,
            moviesDomainDataMapper
        )
    }

    @Singleton
    @Provides
    fun provideMoviesUseCaseImpl(moviesAppRepositoryImpl: MoviesAppRepositoryImpl): MoviesUseCaseImpl {
        return MoviesUseCaseImpl(moviesAppRepositoryImpl)
    }
}