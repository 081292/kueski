package com.kueski.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kueski.tmdb.domain.model.Movie
import com.kueski.tmdb.domain.usecases.FetchMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * MovieDetailsViewModel
 */
class MovieDetailsViewModel(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> get() = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            fetchMovieDetailsUseCase(movieId).collectLatest { details ->
                _movieDetails.value = details
            }
        }
    }
}
