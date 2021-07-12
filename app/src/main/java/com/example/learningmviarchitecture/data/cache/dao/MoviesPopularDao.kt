package com.example.learningmviarchitecture.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.learningmviarchitecture.base.BaseDao
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt

@Dao
interface MoviesPopularDao : BaseDao<MoviePopularEnt> {

    @Query("SELECT * FROM tb_movies_popular where id = :id")
    suspend fun getMovieById(id: Int): MoviePopularEnt

    @Query("SELECT * FROM tb_movies_popular")
    suspend fun getMoviesPopular(): List<MoviePopularEnt>

    @Query("DELETE FROM tb_movies_popular")
    suspend fun deleteAllMovies()
}