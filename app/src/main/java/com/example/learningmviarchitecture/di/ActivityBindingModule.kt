package com.example.learningmviarchitecture.di

import com.example.learningmviarchitecture.feature.favorites.FavoritesActivity
import com.example.learningmviarchitecture.feature.movies.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun provideMoviesActivity(): MoviesActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun provideFavoritesActivity(): FavoritesActivity
}
