package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.local.repository.MovieLocalRepository
import com.kueski.tmdb.domain.mapper.toMovie
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * FetchMovieDetailsUseCase
 *
 * Fetch the details of a Movie from local
 */
class FetchMovieDetailsUseCase(
    private val movieLocalRepository: MovieLocalRepository,
) {

    operator fun invoke(movieId: Int): Flow<Movie> =
        movieLocalRepository.getMovieDetails(movieId)
            .map { movie -> movie.toMovie() }
}
