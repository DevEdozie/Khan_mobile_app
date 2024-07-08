package com.example.khan.model

sealed class BaseResponse<out T>(
    val data: T? = null,
    val message: String? = null
) {
    // Wrap data in this Success class
    class Success<T>(data: T?) : BaseResponse<T>(data = data)

    // Pass error message in this Error class to be used in case of
    // failure response
    class Error<T>(errorMessage: String?) : BaseResponse<T>(message = errorMessage)

    // Loading
    class Loading<T> : BaseResponse<T>()

}