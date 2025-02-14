package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.remote.MovieRepository
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * FetchMovieDetailsUseCase
 *
 * Fetch the details of a Movie from local
 */
class FetchMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<Movie> {
        return movieRepository.getMovieDetails(movieId)
    }
}
