package com.kueski.tmdb.domain.mapper

import com.kueski.tmdb.data.local.entity.GenreEntity
import com.kueski.tmdb.domain.model.Genre


fun Genre.toGenreEntity(): GenreEntity {
    return GenreEntity(
        id = id,
        name = name
    )
}
