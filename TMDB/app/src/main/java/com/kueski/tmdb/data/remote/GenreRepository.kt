package com.kueski.tmdb.data.remote

import com.kueski.tmdb.common.Result
import com.kueski.tmdb.data.local.entity.GenreEntity
import com.kueski.tmdb.domain.model.Genre
import kotlinx.coroutines.flow.Flow

/**
 * GenresRepository
 *
 */
interface GenreRepository {

    // Get from remote the Genres
    suspend fun refreshGenres(): Result<Unit>

    // Get from local the Genres
    fun getGenres(): Flow<List<Genre>>

    // Convert GenreEntity to Genre
    private fun GenreEntity.toGenre(): Genre {
        return Genre(
            id = id,
            name = name
        )
    }

    // Convert Genre to GenreEntity
    private fun Genre.toGenreEntity(): GenreEntity {
        return GenreEntity(
            id = id,
            name = name
        )
    }
}
