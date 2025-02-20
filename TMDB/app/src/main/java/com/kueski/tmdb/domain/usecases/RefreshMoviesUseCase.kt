package com.kueski.tmdb.domain.usecases

import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.common.Result
import com.kueski.tmdb.data.local.repository.MovieLocalRepository
import com.kueski.tmdb.data.remote.repository.MovieRemoteRepository
import com.kueski.tmdb.domain.mapper.toMovieEntity
import java.io.IOException

/**
 * RefreshMoviesUseCase
 *
 * RefreshMoviesUseCase the list of Movies from remote
 */
class RefreshMoviesUseCase(
    private val movieLocalRepository: MovieLocalRepository,
    private val movieRemoteRepository: MovieRemoteRepository,
) {

    suspend operator fun invoke(): Result<Unit> =
        try {
            val movies = movieRemoteRepository.getMovies().results
            movieLocalRepository.deleteAllMovies()
            movieLocalRepository.insertAllMovies(movies.map { it.toMovieEntity() })
            Result.Success(Unit)
        } catch (e: IOException) {
            Result.Error(ApiError.NetworkError)
        } catch (e: Exception) {
            Result.Error(ApiError.UnknownError)
        }
}
