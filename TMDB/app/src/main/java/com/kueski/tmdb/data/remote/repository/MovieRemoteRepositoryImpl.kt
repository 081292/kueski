package com.kueski.tmdb.data.remote.repository

import com.kueski.tmdb.data.remote.model.MovieResponse
import com.kueski.tmdb.data.remote.services.GetPopularMoviesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * MovieRemoteRepositoryImpl
 *
 * Will contain the implementation to CRUD Movies
 */
class MovieRemoteRepositoryImpl(
    private val getPopularMoviesService: GetPopularMoviesService
) : MovieRemoteRepository {

    override suspend fun getMovies(): MovieResponse =
        withContext(Dispatchers.IO) {
            return@withContext getPopularMoviesService.getPopularMovies()
        }
}
