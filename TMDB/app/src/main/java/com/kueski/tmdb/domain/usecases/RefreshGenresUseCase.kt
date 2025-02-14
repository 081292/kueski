package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.remote.GenreRepository
import com.kueski.tmdb.common.Result

/**
 * RefreshGenresUseCase
 *
 * Fetch the list of Genres from remote
 */
class RefreshGenresUseCase(
    private val genreRepository: GenreRepository
) {
    suspend operator fun invoke(): Result<Unit> =
        genreRepository.refreshGenres()
}
