package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.remote.MovieRepository
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * FetchMoviesUseCase
 *
 * Fetch the list of Movies from local
 */
class FetchMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return movieRepository.getMovies()
    }
}
