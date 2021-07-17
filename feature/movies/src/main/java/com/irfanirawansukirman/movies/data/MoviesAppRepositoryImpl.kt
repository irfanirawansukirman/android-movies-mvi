package com.irfanirawansukirman.movies.data

import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepositoryImpl
import com.irfanirawansukirman.movies.data.mapper.MoviesDomainDataMapper
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.data.remote.repository.MoviesRemoteRepositoryImpl
import com.irfanirawansukirman.movies.domain.entity.MoviesPopularEntity
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesAppRepositoryImpl @Inject constructor(
    private val moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl,
    private val moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl,
    private val moviesDomainDataMapper: MoviesDomainDataMapper
) : MoviesAppRepository {

    override suspend fun getRemoteMoviesPopular(): Flow<Resource<List<MoviesPopularEntity>>> {
        return flow {
            try {
                val movies = moviesRemoteRepositoryImpl.getMoviesPopular()
                emit(Resource.Success(moviesDomainDataMapper.fromList(movies)))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun insertMoviePopular(moviesDataModel: MoviesDataModel): Flow<Resource<String>> {
        return flow {
            try {
                // val beforeSize = moviesCacheRepositoryImpl.getCacheMoviesPopular()?.size ?: 0

                moviesCacheRepositoryImpl.insertMoviePopular(moviesDataModel)

                // val afterSize = moviesCacheRepositoryImpl.getCacheMoviesPopular()?.size ?: 0

                emit(Resource.Success(if (true) "Success" else "Failed"))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun getCacheMoviesPopular(): Flow<Resource<List<MoviesPopularEntity>?>> {
        return flow {
            try {
                val movies = moviesCacheRepositoryImpl.getCacheMoviesPopular()
                emit(Resource.Success(moviesDomainDataMapper.fromList(movies)))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}