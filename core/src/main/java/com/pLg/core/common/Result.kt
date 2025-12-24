package com.pLg.core.common

sealed class Result<out T, out E> {
    data class Ok<T>(val value: T) : Result<T, Nothing>()
    data class Err<E>(val error: E) : Result<Nothing, E>()

    inline fun <R> map(transform: (T) -> R): Result<R, E> = when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }

    inline fun <R> andThen(next: (T) -> Result<R, E>): Result<R, E> = when (this) {
        is Ok -> next(value)
        is Err -> this
    }
}
