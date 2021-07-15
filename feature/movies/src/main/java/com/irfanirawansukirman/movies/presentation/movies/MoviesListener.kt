package com.irfanirawansukirman.movies.presentation.movies

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt

interface MoviesListener {

    fun onClickMovie(moviesPopularEnt: MoviesPopularEnt)
}