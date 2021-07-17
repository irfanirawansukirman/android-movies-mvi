package com.irfanirawansukirman.movies.presentation.movies.mapper

import com.irfanirawansukirman.core.Mapper
import com.irfanirawansukirman.movies.domain.entity.MoviesPopularEntity
import com.irfanirawansukirman.movies.presentation.movies.model.MoviesUiModel
import javax.inject.Inject

class MoviesDomainUiMapper @Inject constructor() : Mapper<MoviesPopularEntity, MoviesUiModel> {

    override fun before(previous: MoviesPopularEntity?): MoviesUiModel {
        return MoviesUiModel(
            previous?.id,
            previous?.name,
            previous?.posterPath,
            previous?.release,
            previous?.overview
        )
    }

    override fun after(next: MoviesUiModel?): MoviesPopularEntity {
        return MoviesPopularEntity(
            next?.id,
            next?.name,
            next?.posterPath,
            next?.release,
            next?.overview
        )
    }
}