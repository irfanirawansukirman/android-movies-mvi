package com.irfanirawansukirman.movies.di

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface MoviesComponentProvider {

    fun getMoviesComponent(): MoviesComponent
}