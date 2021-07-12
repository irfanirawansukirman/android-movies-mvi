package com.example.learningmviarchitecture.data.repository

import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import com.example.learningmviarchitecture.data.cache.repository.MoviesCacheRepository
import com.example.learningmviarchitecture.data.cache.repository.MoviesCacheRepositoryImpl
import com.example.learningmviarchitecture.data.remote.model.Result
import com.example.learningmviarchitecture.data.remote.repository.MoviesRemoteRepository
import com.example.learningmviarchitecture.data.remote.repository.MoviesRemoteRepositoryImpl
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl,
    private val moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl
) : MoviesRemoteRepository, MoviesCacheRepository {

    override suspend fun getRemoteMoviesPopular(): List<Result>? {
        return moviesRemoteRepositoryImpl.getRemoteMoviesPopular()
    }

    override suspend fun getCacheMoviesPopular(): List<MoviePopularEnt>? {
        return moviesCacheRepositoryImpl.getCacheMoviesPopular()
    }

    override suspend fun insertCacheMoviePopular(moviePopularEnt: MoviePopularEnt) {
        moviesCacheRepositoryImpl.insertCacheMoviePopular(moviePopularEnt)
    }
}
