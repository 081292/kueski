package com.kueski.tmdb.data.di

import com.kueski.tmdb.data.local.AppDatabase
import org.koin.dsl.module

/**
 * databaseModule
 *
 * Koin Service Locator for all dao´s objects
 */
val databaseModule = module {
    single {
        AppDatabase.getDatabase(get())
    }
    single {
        get<AppDatabase>().genreDao()
    }
    single {
        get<AppDatabase>().movieDao()
    }
}
