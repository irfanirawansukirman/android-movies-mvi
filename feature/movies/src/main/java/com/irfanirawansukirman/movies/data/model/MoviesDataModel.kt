package com.irfanirawansukirman.movies.data.model

data class MoviesDataModel(
    val id: Int? = 0,
    var name: String?,
    val posterPath: String?,
    val release: String?,
    val overview: String?
)