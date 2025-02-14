package com.kueski.tmdb.data.remote.services

import com.kueski.tmdb.data.remote.model.GetGenresResponse
import retrofit2.http.GET

/**
 * GetGenresService
 */
interface GetGenresService {
    @GET("genre/movie/list?language=en")
    suspend fun getGenres(): GetGenresResponse
}