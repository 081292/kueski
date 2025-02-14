package com.kueski.tmdb.domain.di

import com.kueski.tmdb.domain.usecases.FetchGenresUseCase
import com.kueski.tmdb.domain.usecases.FetchMovieDetailsUseCase
import com.kueski.tmdb.domain.usecases.FetchMoviesUseCase
import com.kueski.tmdb.domain.usecases.RefreshGenresUseCase
import com.kueski.tmdb.domain.usecases.RefreshMoviesUseCase
import org.koin.dsl.module

/**
 * useCaseModule
 *
 * Koin Service Locator for all use cases objects
 */
val useCaseModule = module {
    single { FetchGenresUseCase(get()) }
    single { FetchMoviesUseCase(get()) }
    single { FetchMovieDetailsUseCase(get()) }
    single { RefreshMoviesUseCase(get()) }
    single { RefreshGenresUseCase(get()) }
}
