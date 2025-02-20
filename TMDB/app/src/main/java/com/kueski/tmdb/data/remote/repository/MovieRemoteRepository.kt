package com.kueski.tmdb.data.remote.repository

import com.kueski.tmdb.data.remote.model.MovieResponse

/**
 * MovieRemoteRepository
 */
interface MovieRemoteRepository {

    suspend fun getMovies(): MovieResponse
}
