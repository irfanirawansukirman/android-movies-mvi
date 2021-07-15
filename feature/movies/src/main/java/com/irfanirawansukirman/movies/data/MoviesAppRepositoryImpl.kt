package com.irfanirawansukirman.movies.data

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepositoryImpl
import com.irfanirawansukirman.movies.data.remote.MoviesRemoteRepositoryImpl
import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesAppRepositoryImpl @Inject constructor(
    private val moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl,
    private val moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl
) : MoviesAppRepository {

    override suspend fun getRemoteMoviesPopular(): Flow<Resource<MoviesPopularResponse>> {
        return flow {
            try {
                val movies = moviesRemoteRepositoryImpl.getMoviesPopular()
                emit(Resource.Success(movies))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun insertMoviePopular(moviesPopularEnt: MoviesPopularEnt): Flow<Resource<String>> {
        return flow {
            try {
                val beforeSize = moviesCacheRepositoryImpl.getCacheMoviesPopular()?.size ?: 0

                moviesCacheRepositoryImpl.insertMoviePopular(moviesPopularEnt)

                val afterSize = moviesCacheRepositoryImpl.getCacheMoviesPopular()?.size ?: 0

                emit(Resource.Success(if (afterSize > beforeSize) "Success" else "Failed"))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun getCacheMoviesPopular(): Flow<Resource<List<MoviesPopularEnt>?>> {
        return flow {
            try {
                val movies = moviesCacheRepositoryImpl.getCacheMoviesPopular()
                emit(Resource.Success(movies))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}