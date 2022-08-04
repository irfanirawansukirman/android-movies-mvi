package com.irfanirawansukirman.movies.data.cache

import com.irfanirawansukirman.movies.data.model.MoviesDataModel

interface MoviesCacheRepository {

    suspend fun insertMoviePopular(moviesDataModel: MoviesDataModel)

    suspend fun getCacheMoviesPopular(): List<MoviesDataModel>?
}