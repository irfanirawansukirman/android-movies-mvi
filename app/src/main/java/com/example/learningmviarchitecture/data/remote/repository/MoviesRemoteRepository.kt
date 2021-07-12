package com.example.learningmviarchitecture.data.remote.repository

import com.example.learningmviarchitecture.data.remote.model.Result

interface MoviesRemoteRepository {

    suspend fun getRemoteMoviesPopular(): List<Result>?
}