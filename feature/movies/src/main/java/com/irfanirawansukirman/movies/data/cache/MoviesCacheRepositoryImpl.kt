package com.irfanirawansukirman.movies.data.cache

import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.movies.data.cache.mapper.MoviesCacheMapper
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import javax.inject.Inject

class MoviesCacheRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val moviesCacheMapper: MoviesCacheMapper
) : MoviesCacheRepository {

    override suspend fun insertMoviePopular(moviesDataModel: MoviesDataModel) {
        val entity = moviesCacheMapper.after(moviesDataModel)
        if (entity != null) {
            movieDao.insertObject(entity)
        }
    }

    override suspend fun getCacheMoviesPopular(): List<MoviesDataModel>? {
        val movies = movieDao.getAllFavoriteMovies()
        return moviesCacheMapper.fromList(movies)
    }
}