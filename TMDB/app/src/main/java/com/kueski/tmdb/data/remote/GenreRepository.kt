package com.kueski.tmdb.data.remote

import com.kueski.tmdb.data.local.dao.GenreDao
import com.kueski.tmdb.data.local.entity.GenreEntity
import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.common.Result
import com.kueski.tmdb.data.remote.services.GetGenresService
import com.kueski.tmdb.domain.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * GenresRepositoryImpl
 *
 * Will contain the implementation to CRUD Genres
 */
class GenreRepository(
    private val getGenresService: GetGenresService,
    private val genresDao: GenreDao
) {

    // Get from remote the Genres
    suspend fun refreshGenres(): Result<Unit> {
        return try {
            val genresFromApi = getGenresService.getGenres().results
            genresDao.deleteAllGenres()
            genresDao.insertGenres(genresFromApi.map { it.toGenreEntity() })
            Result.Success(Unit)
        } catch (e: IOException) {
            Result.Error(ApiError.NetworkError)
        } catch (e: Exception) {
            Result.Error(ApiError.UnknownError)
        }
    }

    // Get from local the Genres
    fun getGenres(): Flow<List<Genre>> {
        return genresDao.getAllGenres().map { entities ->
            entities.map { it.toGenre() }
        }
    }

    // Convert GenreEntity to Genre
    private fun GenreEntity.toGenre(): Genre {
        return Genre(
            id = id,
            name = name
        )
    }

    // Convert Genre to GenreEntity
    private fun Genre.toGenreEntity(): GenreEntity {
        return GenreEntity(
            id = id,
            name = name
        )
    }
}
