package com.irfanirawansukirman.movies.data.cache

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt

interface MoviesCacheRepository {

    suspend fun insertMoviePopular(moviesPopularEnt: MoviesPopularEnt)

    suspend fun getCacheMoviesPopular(): List<MoviesPopularEnt>?
}