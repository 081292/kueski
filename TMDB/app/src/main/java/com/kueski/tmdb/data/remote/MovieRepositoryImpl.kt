package com.kueski.tmdb.data.remote

import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.data.local.dao.MovieDao
import com.kueski.tmdb.data.local.entity.MovieEntity
import com.kueski.tmdb.data.remote.services.GetPopularMoviesService
import com.kueski.tmdb.domain.model.Movie
import com.kueski.tmdb.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * MovieRepository
 *
 * Will contain the implementation to CRUD Movies
 */
class MovieRepositoryImpl(
    private val getPopularMoviesService: GetPopularMoviesService, // Retrofit
    private val movieDao: MovieDao               // Room
): MovieRepository {

    // Get from remote the Movies
    override suspend fun refreshMovies(): Result<Unit> {
        return try {
            val moviesFromApi = getPopularMoviesService.getPopularMovies().results
            movieDao.deleteAllMovies()
            movieDao.insertMovies(moviesFromApi.map { it.toMovieEntity() })
            Result.Success(Unit)
        } catch (e: IOException) {
            Result.Error(ApiError.NetworkError)
        }  catch (e: Exception) {
            Result.Error(ApiError.UnknownError)
        }
    }

    // Get from local the Movies
    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies().map { entities ->
            entities.map { it.toMovie() }
        }
    }

    // Get from local the details of the Movie by movieId
    override fun getMovieDetails(movieId: Int): Flow<Movie> {
        return movieDao.getMovieDetails(movieId).map { entity ->
            entity.toMovie()
        }
    }

    // Convert MovieEntity to Movie
    private fun MovieEntity.toMovie(): Movie {
        return Movie(
            adult = adult,
            backdropPath = backdropPath,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
            genreIds = genreIds
        )
    }

    // Convert Movie to MovieEntity
    private fun Movie.toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
            genreIds = genreIds
        )
    }
}
