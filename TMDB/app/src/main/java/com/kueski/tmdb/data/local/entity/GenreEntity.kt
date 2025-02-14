package com.kueski.tmdb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * GenreEntity
 */
@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String,
)
