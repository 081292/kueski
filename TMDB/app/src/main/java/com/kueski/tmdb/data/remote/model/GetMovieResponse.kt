package com.kueski.tmdb.data.remote.model

import com.kueski.tmdb.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * MovieResponse
 */
@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
