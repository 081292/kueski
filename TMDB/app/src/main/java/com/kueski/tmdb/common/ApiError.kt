package com.kueski.tmdb.common

sealed class ApiError {
    data object NetworkError : ApiError()
    data object UnknownError : ApiError()
}
