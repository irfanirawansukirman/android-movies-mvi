package com.irfanirawansukirman.remote.data.service

import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getMoviesPopular(@Query("api_key") apiKey: String): MoviesPopularResponse
}