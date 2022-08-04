package com.irfanirawansukirman.movies.data.remote.repository

import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.data.remote.mapper.MoviesNetworkMapper
import com.irfanirawansukirman.remote.BuildConfig
import com.irfanirawansukirman.remote.data.service.MovieService
import javax.inject.Inject

class MoviesRemoteRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val moviesNetworkMapper: MoviesNetworkMapper
) : MoviesRemoteRepository {

    override suspend fun getMoviesPopular(): List<MoviesDataModel> {
        val response = movieService.getMoviesPopular(BuildConfig.TMDB_API_KEY)
        return moviesNetworkMapper.fromList(response.results)
    }
}