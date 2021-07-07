package com.example.learningmviarchitecture.di

import com.example.learningmviarchitecture.movies.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun provideMoviesActivity(): MoviesActivity
}
