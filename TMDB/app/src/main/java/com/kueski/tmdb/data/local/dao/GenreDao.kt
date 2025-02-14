package com.kueski.tmdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kueski.tmdb.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

/**
 * GenreDao
 */
@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun getAllGenres(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(movies: List<GenreEntity>)

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()
}
