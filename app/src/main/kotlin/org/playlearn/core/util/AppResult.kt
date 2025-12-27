package org.playlearn.core.util

sealed class AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>()
    data class Error(val throwable: Throwable) : AppResult<Nothing>()
    object Loading : AppResult<Nothing>()

    inline fun <R> map(mapper: (T) -> R): AppResult<R> = when (this) {
        is Success -> Success(mapper(data))
        is Error -> Error(throwable)
        Loading -> Loading
    }
}
