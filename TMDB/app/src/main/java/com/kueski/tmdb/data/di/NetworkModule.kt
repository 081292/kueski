package com.kueski.tmdb.data.di

import com.kueski.tmdb.data.remote.RetrofitClient
import com.kueski.tmdb.data.remote.services.GetGenresService
import com.kueski.tmdb.data.remote.services.GetPopularMoviesService
import okhttp3.Interceptor
import org.koin.dsl.module

/**
 * networkModule
 *
 * Koin Service Locator for all services´s objects
 */
val networkModule = module {
    single {
        RetrofitClient(
            baseUrl = "https://api.themoviedb.org/3/",
            interceptors = listOf(get<Interceptor>())
        )
    }

    single<Interceptor> {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNmY2YzcwYTMzNTczNGRkNjU1YWExZDVhNmMwMzYwNiIsIm5iZiI6MTU2Mzg5NzA2Mi4zMDA5OTk5LCJzdWIiOiI1ZDM3MmNlNmFiNjg0OTAwMTI4NjIyNDEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.YbAQlXYS_h4hRDQfHVe1x3O5oBPbHgmnJWrSRsSBhLo"
                )
                .build()
            chain.proceed(request)
        }
    }

    single {
        get<RetrofitClient>().createService(GetGenresService::class.java)
    }
    single {
        get<RetrofitClient>().createService(GetPopularMoviesService::class.java)
    }
}
