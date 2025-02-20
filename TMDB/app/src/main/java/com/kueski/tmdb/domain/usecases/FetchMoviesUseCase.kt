package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.data.local.repository.MovieLocalRepository
import com.kueski.tmdb.domain.mapper.toMovie
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * FetchMoviesUseCase
 *
 * Fetch the list of Movies from local
 */
class FetchMoviesUseCase(
    private val movieLocalRepository: MovieLocalRepository
) {

    operator fun invoke(): Flow<List<Movie>> =
        movieLocalRepository.getMovies()
            .map { movies -> movies.map { it.toMovie() } }
}
