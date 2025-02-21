package com.example.gradle.domain.util
///ittsdfsdf
sealed class ResultState<T> {
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val error: ErrorMessage) : ResultState<T>()
}