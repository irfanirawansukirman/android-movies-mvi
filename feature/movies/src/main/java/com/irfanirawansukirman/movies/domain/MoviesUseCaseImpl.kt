package com.irfanirawansukirman.movies.domain

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.movies.data.MoviesAppRepository
import com.irfanirawansukirman.movies.data.MoviesAppRepositoryImpl
import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse
import com.irfanirawansukirman.remote.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCaseImpl @Inject constructor(
    private val moviesAppRepositoryImpl: MoviesAppRepositoryImpl
) : MoviesAppRepository {

    override suspend fun getRemoteMoviesPopular(): Flow<Resource<MoviesPopularResponse>> {
        return moviesAppRepositoryImpl.getRemoteMoviesPopular()
    }

    override suspend fun insertMoviePopular(moviesPopularEnt: MoviesPopularEnt): Flow<Resource<String>> {
        return moviesAppRepositoryImpl.insertMoviePopular(moviesPopularEnt)
    }

    override suspend fun getCacheMoviesPopular(): Flow<Resource<List<MoviesPopularEnt>?>> {
        return moviesAppRepositoryImpl.getCacheMoviesPopular()
    }
}