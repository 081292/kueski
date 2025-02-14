package com.kueski.tmdb.domain.di

import com.kueski.tmdb.domain.usecases.FetchGenresUseCase
import org.koin.dsl.module

/**
 * useCaseModule
 *
 * Koin Service Locator for all use cases objects
 */
val useCaseModule = module {
    single { FetchGenresUseCase(get()) }
}
