package com.kueski.tmdb.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Genre
 */
@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)
