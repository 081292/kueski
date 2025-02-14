package com.kueski.tmdb.ui.di

import com.kueski.tmdb.ui.viewmodel.MovieDetailsViewModel
import com.kueski.tmdb.ui.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * viewModelModule
 *
 * Koin Service Locator for all view models objects
 */
val viewModelModule = module {
    viewModel { MovieViewModel(get(), get(), get(), get()) }
    viewModel { MovieDetailsViewModel(get()) }

}
