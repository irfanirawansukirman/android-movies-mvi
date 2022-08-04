package com.irfanirawansukirman.movies.util

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.domain.entity.MoviesPopularEntity
import com.irfanirawansukirman.remote.data.response.MoviesPopularData
import com.irfanirawansukirman.remote.data.response.MoviesPopularResponse

object DataGenerator {

    fun generateRemoteMoviesPopular(): List<MoviesPopularEntity> {
        val movies1 = MoviesPopularEntity(0, "", "", "", "")
        val movies2 = MoviesPopularEntity(0, "", "", "", "")
        val movies3 = MoviesPopularEntity(0, "", "", "", "")
        val movies4 = MoviesPopularEntity(0, "", "", "", "")
        val movies5 = MoviesPopularEntity(0, "", "", "", "")

        return listOf(movies1, movies2, movies3, movies4, movies5)
    }

    fun generateCacheMoviesPopular(): List<MoviesPopularEntity> {
        val movies1 = MoviesPopularEntity(0, "", "", "", "")
        val movies2 = MoviesPopularEntity(0, "", "", "", "")
        val movies3 = MoviesPopularEntity(0, "", "", "", "")
        val movies4 = MoviesPopularEntity(0, "", "", "", "")
        val movies5 = MoviesPopularEntity(0, "", "", "", "")

        return listOf(movies1, movies2, movies3, movies4, movies5)
    }

    fun generateCacheMoviesPopulars(): List<MoviesPopularEnt> {
        val movies1 = MoviesPopularEnt(0, "", "", "", "")
        val movies2 = MoviesPopularEnt(0, "", "", "", "")
        val movies3 = MoviesPopularEnt(0, "", "", "", "")
        val movies4 = MoviesPopularEnt(0, "", "", "", "")
        val movies5 = MoviesPopularEnt(0, "", "", "", "")

        return listOf(movies1, movies2, movies3, movies4, movies5)
    }

    fun generateMovisPopularData(): List<MoviesDataModel> {
        val movies1 = generateMoviesData()
        val movies2 = generateMoviesData()
        val movies3 = generateMoviesData()
        val movies4 = generateMoviesData()
        val movies5 = generateMoviesData()

        return listOf(movies1, movies2, movies3, movies4, movies5)
    }

    fun generateMoviesData(): MoviesDataModel {
        return MoviesDataModel(0, "", "", "", "")
    }

    fun generateResponseMoviesPopularData(): List<MoviesPopularData> {
        val movie = MoviesPopularData(
            false,
            "",
            listOf(0),
            0,
            "",
            "",
            "",
            0.0,
            "",
            "",
            "",
            false,
            0.0,
            0
        )

        return listOf(movie)
    }
}