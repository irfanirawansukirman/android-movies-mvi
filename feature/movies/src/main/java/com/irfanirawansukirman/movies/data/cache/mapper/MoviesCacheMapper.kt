package com.irfanirawansukirman.movies.data.cache.mapper

import com.irfanirawansukirman.cache.entity.MoviesPopularEnt
import com.irfanirawansukirman.core.Mapper
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import javax.inject.Inject

class MoviesCacheMapper @Inject constructor() : Mapper<MoviesPopularEnt, MoviesDataModel> {

    override fun before(previous: MoviesPopularEnt?): MoviesDataModel {
        return MoviesDataModel(
            previous?.id,
            previous?.name,
            previous?.posterPath,
            previous?.release,
            previous?.overview
        )
    }

    override fun after(next: MoviesDataModel?): MoviesPopularEnt {
        return MoviesPopularEnt(
            next?.id,
            next?.name,
            next?.posterPath,
            next?.release,
            next?.overview
        )
    }
}