package com.example.learningmviarchitecture.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_movies_popular")
data class MoviePopularEnt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = 0,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release")
    val release: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "favorite")
    val favorite: String?
)