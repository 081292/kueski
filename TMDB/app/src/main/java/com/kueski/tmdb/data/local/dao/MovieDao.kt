package com.kueski.tmdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kueski.tmdb.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * MovieDao
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieDetails(movieId: Int): Flow<MovieEntity>
}
