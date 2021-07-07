package com.example.learningmviarchitecture.data.repository

import com.example.learningmviarchitecture.data.model.Result
import com.example.learningmviarchitecture.data.network.ApiInterface
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : MoviesRepository {

    override suspend fun getMoviesPopular(): List<Result> {
        return apiInterface.getMoviesPopular("16241fe7ae72859ad73bfdc8ede14365").results ?: emptyList()
    }
}