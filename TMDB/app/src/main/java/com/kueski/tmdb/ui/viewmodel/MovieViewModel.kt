package com.kueski.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kueski.tmdb.domain.usecases.FetchGenresUseCase
import kotlinx.coroutines.launch

/**
 * MovieViewModel
 */
class MovieViewModel(
    private val fetchGenresUseCase: FetchGenresUseCase
) : ViewModel() {

    init {
        fetchGenres()
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            fetchGenresUseCase(true)
        }
    }
}
