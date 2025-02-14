package com.kueski.tmdb.data.remote.model

import com.kueski.tmdb.domain.model.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * GetGenresResponse
 */
@JsonClass(generateAdapter = true)
data class GetGenresResponse(
    @Json(name = "genres") val results: List<Genre>
)
