package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.remote.MovieRepository
import com.kueski.tmdb.common.Result

/**
 * RefreshMoviesUseCase
 *
 * RefreshMoviesUseCase the list of Movies from remote
 */
class RefreshMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Result<Unit> =
        movieRepository.refreshMovies()
}
