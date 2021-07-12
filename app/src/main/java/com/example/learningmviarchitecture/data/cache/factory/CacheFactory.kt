package com.example.learningmviarchitecture.data.cache.factory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learningmviarchitecture.data.cache.dao.MoviesPopularDao
import com.example.learningmviarchitecture.data.cache.entity.MoviePopularEnt

@Database(entities = [MoviePopularEnt::class], version = 1, exportSchema = false)
abstract class CacheFactory : RoomDatabase() {

    abstract fun moviesPopularDao(): MoviesPopularDao
}