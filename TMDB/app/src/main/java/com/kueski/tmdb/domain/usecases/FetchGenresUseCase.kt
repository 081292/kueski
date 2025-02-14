package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.GenreRepository
import com.kueski.tmdb.domain.model.Genre
import kotlinx.coroutines.flow.Flow

/**
 * FetchGenresUseCase
 *
 * Fetch the list of Genres from local
 * "forceRefresh" will be used to force and update of the list from remote
 */
class FetchGenresUseCase(
    private val genreRepository: GenreRepository
) {
    operator fun invoke(): Flow<List<Genre>> {
        return genreRepository.getGenres()
    }

    suspend operator fun invoke(forceRefresh: Boolean = false) {
        if (forceRefresh) {
            genreRepository.refreshGenres()
        }
    }
}
