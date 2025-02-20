package com.kueski.tmdb.data.local.repository

import com.kueski.tmdb.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * MovieLocalRepository
 */
interface MovieLocalRepository {

    suspend fun insertAllMovies(movies: List<MovieEntity>)
    fun getMovies(): Flow<List<MovieEntity>>
    fun getMovieDetails(movieId: Int): Flow<MovieEntity>
    suspend fun deleteAllMovies()
}
