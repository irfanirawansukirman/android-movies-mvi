package com.irfanirawansukirman.movies.presentation.movies.model

data class MoviesUiModel(
    val id: Int? = 0,
    var name: String?,
    val posterPath: String?,
    val release: String?,
    val overview: String?
)