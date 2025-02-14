package com.kueski.tmdb.data.di

import com.kueski.tmdb.data.remote.GenreRepository
import com.kueski.tmdb.data.remote.GenreRepositoryImpl
import com.kueski.tmdb.data.remote.MovieRepository
import com.kueski.tmdb.data.remote.MovieRepositoryImpl
import org.koin.dsl.module

/**
 * repositoryModule
 *
 * Koin Service Locator for all repositories objects
 */
val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<GenreRepository> { GenreRepositoryImpl(get(), get()) }
}
