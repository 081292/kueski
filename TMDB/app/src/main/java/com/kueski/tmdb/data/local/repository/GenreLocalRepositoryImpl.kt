package com.kueski.tmdb.data.local.repository

import com.kueski.tmdb.data.local.dao.GenreDao
import com.kueski.tmdb.data.local.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

/**
 * GenreLocalRepositoryImpl
 *
 * Will contain the implementation to CRUD Genres
 */
class GenreLocalRepositoryImpl(
    private val genresDao: GenreDao
) : GenreLocalRepository {

    override suspend fun insertGenres(genres: List<GenreEntity>) {
        genresDao.insertGenres(genres)
    }

    override fun getGenres(): Flow<List<GenreEntity>> {
        return genresDao.getAllGenres()
    }

    override suspend fun deleteAllGenres() {
        genresDao.deleteAllGenres()
    }
}
