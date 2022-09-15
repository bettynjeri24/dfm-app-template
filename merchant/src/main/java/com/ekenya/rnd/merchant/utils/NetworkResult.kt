package com.ekenya.rnd.merchant.utils

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Loading<out T>(val data: T? = null) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}
