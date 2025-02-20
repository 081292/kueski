package com.kueski.tmdb.data.local.repository

import com.kueski.tmdb.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

/**
 * GenreLocalRepository
 *
 */
interface GenreLocalRepository {

    suspend fun insertGenres(genres: List<GenreEntity>)
    fun getGenres(): Flow<List<GenreEntity>>
    suspend fun deleteAllGenres()
}
