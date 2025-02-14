package com.kueski.tmdb.data.di

import com.kueski.tmdb.data.remote.GenreRepository
import com.kueski.tmdb.data.remote.MovieRepository
import org.koin.dsl.module

/**
 * repositoryModule
 *
 * Koin Service Locator for all repositories objects
 */
val repositoryModule = module {
    single { GenreRepository(get(), get()) }
    single { MovieRepository(get(), get()) }
}
