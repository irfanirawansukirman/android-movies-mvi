package com.irfanirawansukirman.movies.domain.entity

data class MoviesPopularEntity(
    val id: Int? = 0,
    var name: String?,
    val posterPath: String?,
    val release: String?,
    val overview: String?
)