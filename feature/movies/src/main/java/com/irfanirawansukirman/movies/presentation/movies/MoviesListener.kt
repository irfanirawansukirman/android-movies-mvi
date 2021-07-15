package com.irfanirawansukirman.movies.presentation.movies

import com.irfanirawansukirman.movies.data.model.MoviesDataModel

interface MoviesListener {

    fun onClickMovie(moviesDataModel: MoviesDataModel)
}