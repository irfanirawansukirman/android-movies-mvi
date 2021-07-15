package com.irfanirawansukirman.movies.data

import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.domain.entity.MoviesPopularEntity
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesAppRepository {

    suspend fun getRemoteMoviesPopular(): Flow<Resource<List<MoviesPopularEntity>>>

    suspend fun insertMoviePopular(moviesDataModel: MoviesDataModel): Flow<Resource<String>>

    suspend fun getCacheMoviesPopular(): Flow<Resource<List<MoviesPopularEntity>?>>
}