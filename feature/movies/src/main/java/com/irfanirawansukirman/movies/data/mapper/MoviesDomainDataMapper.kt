package com.irfanirawansukirman.movies.data.mapper

import com.irfanirawansukirman.core.Mapper
import com.irfanirawansukirman.movies.data.model.MoviesDataModel
import com.irfanirawansukirman.movies.domain.entity.MoviesPopularEntity
import javax.inject.Inject

class MoviesDomainDataMapper @Inject constructor() : Mapper<MoviesDataModel, MoviesPopularEntity> {

    override fun before(previous: MoviesDataModel?): MoviesPopularEntity? {
        return MoviesPopularEntity(
            previous?.id,
            previous?.name,
            previous?.posterPath,
            previous?.release,
            previous?.overview
        )
    }

    override fun after(next: MoviesPopularEntity?): MoviesDataModel? {
        return MoviesDataModel(
            next?.id,
            next?.name,
            next?.posterPath,
            next?.release,
            next?.overview
        )
    }
}