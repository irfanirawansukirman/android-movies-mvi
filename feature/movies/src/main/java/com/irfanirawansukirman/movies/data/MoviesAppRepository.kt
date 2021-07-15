package com.irfanirawansukirman.movies.data

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesAppRepository {

    suspend fun getRemoteMoviesPopular(): Flow<Resource<MoviesPopularResponse>>

    suspend fun insertMoviePopular(moviesPopularEnt: MoviesPopularEnt): Flow<Resource<String>>

    suspend fun getCacheMoviesPopular(): Flow<Resource<List<MoviesPopularEnt>?>>
}