package com.kueski.tmdb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kueski.tmdb.data.local.dao.GenreDao
import com.kueski.tmdb.data.local.entity.GenreEntity

/**
 * AppDatabase
 */
@Database(
    entities = [GenreEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genresDao(): GenreDao

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
