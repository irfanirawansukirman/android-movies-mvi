package com.example.learningmviarchitecture.data.cache.repository

import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt

interface MoviesCacheRepository {

    suspend fun getCacheMoviesPopular(): List<MoviePopularEnt>?

    suspend fun insertCacheMoviePopular(moviePopularEnt: MoviePopularEnt)
}