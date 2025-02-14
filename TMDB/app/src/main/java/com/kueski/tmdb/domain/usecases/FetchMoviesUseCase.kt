package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.remote.MovieRepository
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * FetchMoviesUseCase
 *
 * Fetch the list of Movies from local
 * "forceRefresh" will be used to force and update of the list from remote
 */
class FetchMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return movieRepository.getMovies()
    }

    suspend operator fun invoke(forceRefresh: Boolean = false) {
        if (forceRefresh) {
            movieRepository.refreshMovies()
        }
    }
}
