package com.irfanirawansukirman.core.di

import com.irfanirawansukirman.core.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {

    @Singleton
    @Provides
    fun provideCoroutineContextProvider(): CoroutinesContextProvider = CoroutinesContextProvider()
}