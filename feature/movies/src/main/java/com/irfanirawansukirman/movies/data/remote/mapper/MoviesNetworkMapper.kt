package com.irfanirawansukirman.movies.data.remote.mapper

import com.irfanirawansukirman.core.Mapper
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.remote.data.response.MoviesPopularData
import javax.inject.Inject

class MoviesNetworkMapper @Inject constructor() : Mapper<MoviesPopularData, MoviesDataModel> {

    override fun before(previous: MoviesPopularData?): MoviesDataModel {
        return MoviesDataModel(
            previous?.id,
            previous?.originalTitle,
            previous?.posterPath,
            previous?.releaseDate,
            previous?.overview
        )
    }

    override fun after(next: MoviesDataModel?): MoviesPopularData {
        return MoviesPopularData(
            false,
            "",
            listOf(0),
            next?.id,
            "",
            next?.name,
            next?.overview,
            0.0,
            next?.posterPath,
            next?.release,
            next?.name,
            false,
            0.0,
            0
        )
    }
}