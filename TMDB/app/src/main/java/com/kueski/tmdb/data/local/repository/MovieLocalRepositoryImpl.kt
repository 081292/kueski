package com.kueski.tmdb.data.local.repository

import com.kueski.tmdb.data.local.dao.MovieDao
import com.kueski.tmdb.data.local.entity.MovieEntity
import com.kueski.tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * MovieLocalRepositoryImpl
 *
 * Will contain the implementation to CRUD Movies
 */
class MovieLocalRepositoryImpl(
    private val movieDao: MovieDao
) : MovieLocalRepository {

    override suspend fun insertAllMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    override fun getMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    override fun getMovieDetails(movieId: Int): Flow<MovieEntity> {
        return movieDao.getMovieDetails(movieId)
    }

    override suspend fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}
