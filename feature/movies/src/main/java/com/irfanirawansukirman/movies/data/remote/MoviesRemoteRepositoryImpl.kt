package com.irfanirawansukirman.movies.data.remote

import com.irfanirawansukirman.remote.BuildConfig
import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse
import com.irfanirawansukirman.remote.data.service.MovieService
import javax.inject.Inject

class MoviesRemoteRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MoviesRemoteRepository {

    override suspend fun getMoviesPopular(): MoviesPopularResponse {
        return movieService.getMoviesPopular(BuildConfig.TMDB_API_KEY)
    }
}