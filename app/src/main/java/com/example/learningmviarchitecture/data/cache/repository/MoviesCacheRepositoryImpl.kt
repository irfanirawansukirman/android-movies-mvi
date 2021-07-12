package com.example.learningmviarchitecture.data.cache.repository

import com.example.learningmviarchitecture.data.cache.dao.MoviesPopularDao
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt
import javax.inject.Inject

class MoviesCacheRepositoryImpl @Inject constructor(
    private val moviesPopularDao: MoviesPopularDao
) : MoviesCacheRepository {

    override suspend fun getCacheMoviesPopular(): List<MoviePopularEnt>? {
        return moviesPopularDao.getMoviesPopular()
    }

    override suspend fun insertCacheMoviePopular(moviePopularEnt: MoviePopularEnt) {
        moviesPopularDao.insert(moviePopularEnt)
    }
}
