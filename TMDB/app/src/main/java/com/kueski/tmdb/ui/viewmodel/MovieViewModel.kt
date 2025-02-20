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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface MovieUiState {
    data object Loading : MovieUiState
    data class Success(val movies: Map<String, List<Movie>>) : MovieUiState
}

/**
 * MovieViewModel
 */
class MovieViewModel(
    fetchGenresUseCase: FetchGenresUseCase,
    fetchMoviesUseCase: FetchMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    private val refreshGenresUseCase: RefreshGenresUseCase
) : ViewModel() {

    private val _error = MutableStateFlow<ApiError?>(null)
    val error: StateFlow<ApiError?> get() = _error

    init {
        fetchGenres()
        fetchMovies()
    }

    private val moviesByGenre: Flow<Map<String, List<Movie>>> =
        combine(fetchMoviesUseCase(), fetchGenresUseCase()) { movies, genres ->
            genres.associate { genre ->
                genre.name to movies.filter { movie -> movie.genreIds.contains(genre.id) }
            }
        }

    val moviesUiState: StateFlow<MovieUiState> = moviesByGenre.map { movies ->
        MovieUiState.Success(movies = movies)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MovieUiState.Loading,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000)
    )

    fun fetchGenres() {
        viewModelScope.launch {
            when (val result = refreshGenresUseCase()) {
                is Result.Success -> {
                    // TODO: Notify data was refreshed
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
                    // TODO: Notify data was refreshed
                    _error.value = null
                }

                is Result.Error -> {
                    _error.value = result.error
                }
            }
        }
    }

    fun refreshMovies() {
        _error.value = null
        fetchMovies()
        fetchGenres()
    }
}
