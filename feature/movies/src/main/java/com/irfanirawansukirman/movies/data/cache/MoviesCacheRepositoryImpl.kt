package com.irfanirawansukirman.movies.data.cache

import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import javax.inject.Inject

class MoviesCacheRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
): MoviesCacheRepository {

    override suspend fun insertMoviePopular(moviesPopularEnt: MoviesPopularEnt) {
        movieDao.insert(moviesPopularEnt)
    }

    override suspend fun getCacheMoviesPopular(): List<MoviesPopularEnt>? {
        return movieDao.getAllFavoriteMovies()
    }
}