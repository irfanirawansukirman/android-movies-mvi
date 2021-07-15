package com.irfanirawansukirman.movies.domain

import com.irfanirawansukirman.movies.data.MoviesAppRepository
import com.irfanirawansukirman.movies.data.MoviesAppRepositoryImpl
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.domain.entity.MoviesPopularEntity
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCaseImpl @Inject constructor(
    private val moviesAppRepositoryImpl: MoviesAppRepositoryImpl
) : MoviesAppRepository {

    override suspend fun getRemoteMoviesPopular(): Flow<Resource<List<MoviesPopularEntity>>> {
        return moviesAppRepositoryImpl.getRemoteMoviesPopular()
    }

    override suspend fun insertMoviePopular(moviesDataModel: MoviesDataModel): Flow<Resource<String>> {
        return moviesAppRepositoryImpl.insertMoviePopular(moviesDataModel)
    }

    override suspend fun getCacheMoviesPopular(): Flow<Resource<List<MoviesPopularEntity>?>> {
        return moviesAppRepositoryImpl.getCacheMoviesPopular()
    }
}