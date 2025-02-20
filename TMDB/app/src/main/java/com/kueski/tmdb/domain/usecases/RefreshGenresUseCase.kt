package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.common.Result
import com.kueski.tmdb.data.local.repository.GenreLocalRepository
import com.kueski.tmdb.data.remote.repository.GenreRemoteRepository
import com.kueski.tmdb.domain.mapper.toGenreEntity
import java.io.IOException

/**
 * RefreshGenresUseCase
 *
 * Fetch the list of Genres from remote
 */
class RefreshGenresUseCase(
    private val genreRemoteRepository: GenreRemoteRepository,
    private val genreLocalRepository: GenreLocalRepository,
) {
    suspend operator fun invoke(): Result<Unit> =
        try {
            val genres = genreRemoteRepository.refreshGenres().results
            genreLocalRepository.deleteAllGenres()
            genreLocalRepository.insertGenres(genres.map { it.toGenreEntity() })
            Result.Success(Unit)
        } catch (e: IOException) {
            Result.Error(ApiError.NetworkError)
        } catch (e: Exception) {
            Result.Error(ApiError.UnknownError)
        }
}
