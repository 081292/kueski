package com.kueski.tmdb.data.di

import com.kueski.tmdb.data.local.repository.GenreLocalRepository
import com.kueski.tmdb.data.local.repository.GenreLocalRepositoryImpl
import com.kueski.tmdb.data.local.repository.MovieLocalRepository
import com.kueski.tmdb.data.local.repository.MovieLocalRepositoryImpl
import com.kueski.tmdb.data.remote.repository.GenreRemoteRepository
import com.kueski.tmdb.data.remote.repository.GenreRemoteRepositoryImpl
import com.kueski.tmdb.data.remote.repository.MovieRemoteRepository
import com.kueski.tmdb.data.remote.repository.MovieRemoteRepositoryImpl
import org.koin.dsl.module

/**
 * repositoryModule
 *
 * Koin Service Locator for all repositories objects
 */
val repositoryModule = module {
    single<MovieLocalRepository> { MovieLocalRepositoryImpl(get()) }
    single<MovieRemoteRepository> { MovieRemoteRepositoryImpl(get()) }
    single<GenreLocalRepository> { GenreLocalRepositoryImpl(get()) }
    single<GenreRemoteRepository> { GenreRemoteRepositoryImpl(get()) }
}
