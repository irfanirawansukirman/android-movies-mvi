package com.example.learningmviarchitecture.data.remote.repository

import android.util.Log
import com.example.learningmviarchitecture.BuildConfig
import com.example.learningmviarchitecture.data.remote.model.Result
import com.example.learningmviarchitecture.data.remote.ApiInterface
import javax.inject.Inject

class MoviesRemoteRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : MoviesRemoteRepository {

    override suspend fun getRemoteMoviesPopular(): List<Result>? {
        Log.d("Masuk Remote", "Sukses")
        return apiInterface.getMoviesPopular(BuildConfig.TMDB_API_KEY).results
    }
}