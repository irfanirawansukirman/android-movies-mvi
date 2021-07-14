package com.irfanirawansukirman.movies.data.remote

import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse

interface MoviesRemoteRepository {

    suspend fun getMoviesPopular(): MoviesPopularResponse
}