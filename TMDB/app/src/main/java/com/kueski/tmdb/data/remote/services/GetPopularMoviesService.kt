package com.kueski.tmdb.data.remote.services

import com.kueski.tmdb.data.remote.model.MovieResponse
import retrofit2.http.GET

/**
 * GetPopularMoviesService
 */
interface GetPopularMoviesService {
    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getPopularMovies(): MovieResponse
}
