package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.remote.GenreRepository
import com.kueski.tmdb.domain.model.Genre
import kotlinx.coroutines.flow.Flow

/**
 * FetchGenresUseCase
 *
 * Fetch the list of Genres from local
 */
class FetchGenresUseCase(
    private val genreRepository: GenreRepository
) {
    operator fun invoke(): Flow<List<Genre>> {
        return genreRepository.getGenres()
    }
}
