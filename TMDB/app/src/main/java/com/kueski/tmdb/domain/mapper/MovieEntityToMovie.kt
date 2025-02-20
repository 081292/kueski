package com.kueski.tmdb.domain.mapper

import com.kueski.tmdb.data.local.entity.MovieEntity
import com.kueski.tmdb.domain.model.Movie

fun MovieEntity.toMovie(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genreIds = genreIds
    )
}
