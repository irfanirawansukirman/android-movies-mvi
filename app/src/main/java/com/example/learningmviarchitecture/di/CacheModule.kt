package com.example.learningmviarchitecture.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.learningmviarchitecture.data.cache.dao.MoviesPopularDao
import com.example.learningmviarchitecture.data.cache.factory.CacheFactory
import com.example.learningmviarchitecture.data.cache.repository.MoviesCacheRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheFactory(application: Application): CacheFactory = Room
        .databaseBuilder(application, CacheFactory::class.java, "db_movies")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMoviesPopularDao(cacheFactory: CacheFactory): MoviesPopularDao =
        cacheFactory.moviesPopularDao()

    @Singleton
    @Provides
    fun provideMoviesCacheRepositoryImpl(moviesPopularDao: MoviesPopularDao): MoviesCacheRepositoryImpl {
        return MoviesCacheRepositoryImpl(moviesPopularDao)
    }

    @Singleton
    @Provides
    fun provideSecretPreference(application: Application): SharedPreferences {
        val masterKey = MasterKey.Builder(application)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            application,
            "my_secret_preference",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}