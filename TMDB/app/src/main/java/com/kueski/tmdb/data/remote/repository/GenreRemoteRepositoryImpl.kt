package com.kueski.tmdb.data.remote.repository

import com.kueski.tmdb.data.remote.model.GetGenresResponse
import com.kueski.tmdb.data.remote.services.GetGenresService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * GenreRemoteRepositoryImpl
 *
 * Will contain the implementation to CRUD Genres
 */
class GenreRemoteRepositoryImpl(
    private val getGenresService: GetGenresService,
) : GenreRemoteRepository {

    override suspend fun refreshGenres(): GetGenresResponse =
        withContext(Dispatchers.IO) {
            return@withContext getGenresService.getGenres()
        }
}
