package com.kueski.tmdb.domain.mapper

import com.kueski.tmdb.data.local.entity.GenreEntity
import com.kueski.tmdb.domain.model.Genre

fun GenreEntity.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}
