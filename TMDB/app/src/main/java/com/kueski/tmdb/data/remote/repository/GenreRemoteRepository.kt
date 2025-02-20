package com.kueski.tmdb.data.remote.repository

import com.kueski.tmdb.data.remote.model.GetGenresResponse

/**
 * GenreRemoteRepository
 *
 */
interface GenreRemoteRepository {

    suspend fun refreshGenres(): GetGenresResponse
}
