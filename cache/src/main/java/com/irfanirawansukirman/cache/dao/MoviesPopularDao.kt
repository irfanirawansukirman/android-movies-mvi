package com.irfanirawansukirman.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.irfanirawansukirman.cache.base.BaseDao
import com.irfanirawansukirman.cache.entity.MoviesPopularEnt

@Dao
interface MoviesPopularDao : BaseDao<MoviesPopularEnt> {

    @Query("SELECT * FROM tb_movies_popular where id = :id")
    suspend fun getMovieById(id: Int): MoviesPopularEnt

    @Query("SELECT * FROM tb_movies_popular")
    suspend fun getAllFavoriteMovies(): List<MoviesPopularEnt>

    @Query("DELETE FROM tb_movies_popular")
    suspend fun deleteAllMovies()
}