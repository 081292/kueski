package com.kueski.tmdb

import android.app.Application
import com.kueski.tmdb.data.di.databaseModule
import com.kueski.tmdb.data.di.networkModule
import com.kueski.tmdb.data.di.repositoryModule
import com.kueski.tmdb.domain.di.useCaseModule
import com.kueski.tmdb.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * TMDBApplication
 */
class TMDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
