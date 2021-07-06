package com.example.learningmviarchitecture.data.network

import com.example.learningmviarchitecture.data.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getMoviesPopular(@Query("api_key") apiKey: String): Movies
}
