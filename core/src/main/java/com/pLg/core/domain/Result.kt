package com.pLg.core.domain

/**
 * Functional result type for domain operations.
 * Never throws; carries typed failures.
 */
sealed class Result<out T, out E> {
    data class Ok<T>(val value: T) : Result<T, Nothing>()
    data class Err<E>(val error: E) : Result<Nothing, E>()

    inline fun <R> map(transform: (T) -> R): Result<R, E> = when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }

    inline fun <F> mapError(transform: (E) -> F): Result<T, F> = when (this) {
        is Ok -> this
        is Err -> Err(transform(error))
    }

    inline fun <R> andThen(next: (T) -> Result<R, E>): Result<R, E> = when (this) {
        is Ok -> next(value)
        is Err -> this
    }

    fun getOrNull(): T? = when (this) {
        is Ok -> value
        is Err -> null
    }

    fun errorOrNull(): E? = when (this) {
        is Ok -> null
        is Err -> error
    }

    companion object {
        fun <T> ok(value: T): Result<T, Nothing> = Ok(value)
        fun <E> err(error: E): Result<Nothing, E> = Err(error)
    }
}
