package com.kueski.tmdb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**
 * MovieEntity
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    @TypeConverters(GenreList::class)
    val genreIds: List<Int>
)

class GenreList {
    @TypeConverter
    fun genreListToString(genreList: List<Int>): String {
        return genreList.joinToString(",")
    }

    @TypeConverter
    fun stringToGenreList(string: String): List<Int> {
        return string.split(",").map { it.toInt() }
    }
}
