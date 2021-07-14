package com.irfanirawansukirman.core.base

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