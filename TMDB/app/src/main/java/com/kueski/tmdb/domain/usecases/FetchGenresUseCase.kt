package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.local.repository.GenreLocalRepository
import com.kueski.tmdb.domain.mapper.toGenre
import com.kueski.tmdb.domain.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * FetchGenresUseCase
 *
 * Fetch the list of Genres from local
 */
class FetchGenresUseCase(
    private val genreLocalRepository: GenreLocalRepository,
) {

    operator fun invoke(): Flow<List<Genre>> =
        genreLocalRepository.getGenres().map { genres ->
            genres.map { it.toGenre() }
        }
}
