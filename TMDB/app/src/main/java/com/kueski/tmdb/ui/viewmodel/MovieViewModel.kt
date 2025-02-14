package com.kueski.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kueski.tmdb.domain.model.Movie
import com.kueski.tmdb.domain.usecases.FetchGenresUseCase
import com.kueski.tmdb.domain.usecases.FetchMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * MovieViewModel
 */
class MovieViewModel(
    private val fetchGenresUseCase: FetchGenresUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    init {
        fetchGenres()
        fetchMovies()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            fetchGenresUseCase(true)
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            fetchMoviesUseCase(true)
        }
    }

    fun getMoviesByGenreFlow(): Flow<Map<String, List<Movie>>> {
        return combine(fetchGenresUseCase(), fetchMoviesUseCase()) { genres, movies ->
            genres.associate { genre ->
                genre.name to movies.filter { movie -> movie.genreIds.contains(genre.id) }
            }
        }
    }
}
