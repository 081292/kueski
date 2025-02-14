package com.kueski.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kueski.tmdb.common.Result
import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.domain.model.Movie
import com.kueski.tmdb.domain.usecases.FetchGenresUseCase
import com.kueski.tmdb.domain.usecases.FetchMoviesUseCase
import com.kueski.tmdb.domain.usecases.RefreshGenresUseCase
import com.kueski.tmdb.domain.usecases.RefreshMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

/**
 * MovieViewModel
 */
class MovieViewModel(
    private val fetchGenresUseCase: FetchGenresUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    private val refreshGenresUseCase: RefreshGenresUseCase
) : ViewModel() {

    init {
        fetchGenres()
        fetchMovies()
    }

    private val _error = MutableStateFlow<ApiError?>(null)
    val error: StateFlow<ApiError?> get() = _error

    fun fetchGenres() {
        viewModelScope.launch {
            when (val result = refreshGenresUseCase()) {
                is Result.Success -> {
                    _error.value = null
                }

                is Result.Error -> {
                    _error.value = result.error
                }
            }
        }
    }

    fun fetchMovies() {
        viewModelScope.launch {
            when (val result = refreshMoviesUseCase()) {
                is Result.Success -> {
                    _error.value = null
                }

                is Result.Error -> {
                    _error.value = result.error
                }
            }
        }
    }

    fun getMoviesByGenreFlow(): Flow<Map<String, List<Movie>>> {
        return combine(fetchGenresUseCase(), fetchMoviesUseCase()) { genres, movies ->
            genres.associate { genre ->
                genre.name to movies.filter { movie -> movie.genreIds.contains(genre.id) }
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
