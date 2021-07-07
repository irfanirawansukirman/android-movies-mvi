package com.example.learningmviarchitecture.data.repository

import com.example.learningmviarchitecture.data.model.Result

interface MoviesRepository {

    suspend fun getMoviesPopular(): List<Result>?
}