package com.kueski.tmdb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kueski.tmdb.data.local.dao.GenreDao
import com.kueski.tmdb.data.local.dao.MovieDao
import com.kueski.tmdb.data.local.entity.GenreEntity
import com.kueski.tmdb.data.local.entity.GenreList
import com.kueski.tmdb.data.local.entity.MovieEntity

/**
 * AppDatabase
 */
@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class
               ],
    version = 1
)
@TypeConverters(
    GenreList::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
