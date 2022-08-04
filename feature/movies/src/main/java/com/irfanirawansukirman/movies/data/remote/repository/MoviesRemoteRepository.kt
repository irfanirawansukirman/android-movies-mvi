package com.irfanirawansukirman.movies.data.remote.repository

import com.irfanirawansukirman.movies.data.model.MoviesDataModel

interface MoviesRemoteRepository {

    suspend fun getMoviesPopular(): List<MoviesDataModel>
}