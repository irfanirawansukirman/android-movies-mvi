package com.irfanirawansukirman.cache.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.irfanirawansukirman.cache.BuildConfig
import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.cache.factory.CacheFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheFactory(application: Application): CacheFactory = Room
        .databaseBuilder(application, CacheFactory::class.java, BuildConfig.DB_MOVIES)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMovieDao(cacheFactory: CacheFactory): MovieDao = cacheFactory.movieDao()

    @Singleton
    @Provides
    fun provideSecretPreference(application: Application): SharedPreferences {
        val masterKey = MasterKey.Builder(application)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            application,
            BuildConfig.SECRET_PREFERENCE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}