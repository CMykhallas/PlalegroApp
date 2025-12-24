package com.pLg.data.util

sealed class Result<out T> {
    data class Ok<T>(val value: T): Result<T>()
    data class Err(val error: DataError): Result<Nothing>()

    inline fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Ok -> Ok(transform(value))
        is Err -> Err(error)
    }

    inline fun onSuccess(block: (T) -> Unit): Result<T> = apply { if (this is Ok) block(value) }
    inline fun onError(block: (DataError) -> Unit): Result<T> = apply { if (this is Err) block(error) }
}

sealed class DataError(open val message: String, open val cause: Throwable? = null) {
    data class NotFound(override val message: String = "Not found"): DataError(message)
    data class Network(override val message: String, override val cause: Throwable? = null): DataError(message, cause)
    data class Serialization(override val message: String, override val cause: Throwable? = null): DataError(message, cause)
    data class Storage(override val message: String, override val cause: Throwable? = null): DataError(message, cause)
    data class Validation(override val message: String): DataError(message)
    data class Unknown(override val message: String, override val cause: Throwable? = null): DataError(message, cause)
}
