package com.kueski.tmdb.data.remote

import com.kueski.tmdb.common.Result
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * MovieRepository
 *
 */
interface MovieRepository {

    // Get from remote the Movies
    suspend fun refreshMovies(): Result<Unit>

    // Get from local the Movies
    fun getMovies(): Flow<List<Movie>>

    // Get from local the details of the Movie by movieId
    fun getMovieDetails(movieId: Int): Flow<Movie>
}
