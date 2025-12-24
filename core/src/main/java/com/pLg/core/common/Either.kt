package com.pLg.core.common

/**
 * Representa um valor que pode ser de dois tipos:
 * - Left (geralmente usado para erros ou falhas)
 * - Right (geralmente usado para sucesso ou resultado válido)
 */
sealed class Either<out L, out R> {

    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    val isLeft: Boolean get() = this is Left<L>
    val isRight: Boolean get() = this is Right<R>

    fun leftOrNull(): L? = (this as? Left<L>)?.value
    fun rightOrNull(): R? = (this as? Right<R>)?.value

    inline fun <T> fold(onLeft: (L) -> T, onRight: (R) -> T): T = when (this) {
        is Left -> onLeft(value)
        is Right -> onRight(value)
    }

    inline fun <T> mapRight(transform: (R) -> T): Either<L, T> = when (this) {
        is Left -> this
        is Right -> Right(transform(value))
    }

    inline fun <T> mapLeft(transform: (L) -> T): Either<T, R> = when (this) {
        is Left -> Left(transform(value))
        is Right -> this
    }

    companion object {
        fun <L> left(value: L): Either<L, Nothing> = Left(value)
        fun <R> right(value: R): Either<Nothing, R> = Right(value)
    }
}
