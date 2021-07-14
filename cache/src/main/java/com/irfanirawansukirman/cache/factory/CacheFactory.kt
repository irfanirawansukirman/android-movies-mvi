package com.irfanirawansukirman.cache.factory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.cache.entity.MoviesPopularEnt

@Database(entities = [MoviesPopularEnt::class], version = 8, exportSchema = false)
abstract class CacheFactory : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}