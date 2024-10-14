package com.example.meintasty.feature

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Failure<Nothing>(val msg: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()

}
