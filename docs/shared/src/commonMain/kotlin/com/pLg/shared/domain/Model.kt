package com.pLg.shared.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Result funcional multiplataforma
public sealed class Result<out T, out E> {
    public data class Ok<T>(val value: T) : Result<T, Nothing>()
    public data class Err<E>(val error: E) : Result<Nothing, E>()

    public inline fun <R> map(transform: (T) -> R): Result<R, E> = when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }

    public inline fun <F> mapError(transform: (E) -> F): Result<T, F> = when (this) {
        is Ok -> this
        is Err -> Err(transform(error))
    }

    public inline fun <R> andThen(next: (T) -> Result<R, E>): Result<R, E> = when (this) {
        is Ok -> next(value)
        is Err -> this
    }

    public fun getOrNull(): T? = (this as? Ok<T>)?.value
    public fun errorOrNull(): E? = (this as? Err<E>)?.error

    public companion object {
        public fun <T> ok(value: T): Result<T, Nothing> = Ok(value)
        public fun <E> err(error: E): Result<Nothing, E> = Err(error)
    }
}

public sealed interface Failure {
    public val message: String
}

public sealed class ValidationFailure(override val message: String) : Failure {
    public object Empty : ValidationFailure("Value must not be empty")
    public object TooShort : ValidationFailure("Value is too short")
    public object TooLong : ValidationFailure("Value is too long")
    public object InvalidFormat : ValidationFailure("Invalid format")
    public object OutOfRange : ValidationFailure("Value out of allowed range")
    public data class Custom(override val message: String) : ValidationFailure(message)
}

@Serializable
public data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String
)

@Serializable
public data class ChildProfileDto(
    @SerialName("id") val id: String,
    @SerialName("display_name") val displayName: String,
    @SerialName("age") val age: Int,
    @SerialName("preferred_locale") val preferredLocale: String
)

@Serializable
public data class ContentPackDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("supported_locales") val supportedLocales: List<String>
)

// Value Objects comuns
public object Validators {
    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    private val localeRegex = Regex("^[a-z]{2}(?:-[A-Z]{2})?\$")
    private val idRegex = Regex("^[a-z0-9]+(?:[-_][a-z0-9]+)*\$")

    public fun nonEmpty(value: String, minLen: Int = 1, maxLen: Int = 256): Result<String, ValidationFailure> {
        val t = value.trim()
        if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
        if (t.length < minLen) return Result.err(ValidationFailure.TooShort)
        if (t.length > maxLen) return Result.err(ValidationFailure.TooLong)
        return Result.ok(t)
    }

    public fun email(value: String): Result<String, ValidationFailure> {
        val t = value.trim()
        if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
        return if (emailRegex.matches(t)) Result.ok(t) else Result.err(ValidationFailure.InvalidFormat)
    }

    public fun locale(value: String): Result<String, ValidationFailure> {
        val t = value.trim()
        if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
        return if (localeRegex.matches(t)) Result.ok(t) else Result.err(ValidationFailure.InvalidFormat)
    }

    public fun id(value: String): Result<String, ValidationFailure> {
        val t = value.trim()
        if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
        if (t.length > 64) return Result.err(ValidationFailure.TooLong)
        return if (idRegex.matches(t)) Result.ok(t) else Result.err(ValidationFailure.InvalidFormat)
    }

    public fun age(value: Int, min: Int = 0, max: Int = 18): Result<Int, ValidationFailure> {
        return if (value in min..max) Result.ok(value) else Result.err(ValidationFailure.OutOfRange)
    }
}

// Use cases multiplataforma
public object UseCases {
    public fun registerUser(rawName: String, rawEmail: String): Result<UserDto, Failure> {
        val name = Validators.nonEmpty(rawName, 2, 64).mapError { it as Failure }
        val email = Validators.email(rawEmail).mapError { it as Failure }

        return when {
            name is Result.Err -> Result.err(name.error)
            email is Result.Err -> Result.err(email.error)
            else -> {
                val id = IdGenerator.uuid()
                Result.ok(UserDto(id = id, name = (name as Result.Ok).value, email = (email as Result.Ok).value))
            }
        }
    }

    public fun createChildProfile(rawDisplayName: String, rawAge: Int, rawLocale: String): Result<ChildProfileDto, Failure> {
        val name = Validators.nonEmpty(rawDisplayName, 1, 40).mapError { it as Failure }
        val age = Validators.age(rawAge).mapError { it as Failure }
        val locale = Validators.locale(rawLocale).mapError { it as Failure }

        return when {
            name is Result.Err -> Result.err(name.error)
            age is Result.Err -> Result.err(age.error)
            locale is Result.Err -> Result.err(locale.error)
            else -> Result.ok(
                ChildProfileDto(
                    id = IdGenerator.uuid(),
                    displayName = (name as Result.Ok).value,
                    age = (age as Result.Ok).value,
                    preferredLocale = (locale as Result.Ok).value
                )
            )
        }
    }

    public fun defineContentPack(rawId: String, rawTitle: String, rawDescription: String, rawLocales: List<String>): Result<ContentPackDto, Failure> {
        val id = Validators.id(rawId).mapError { it as Failure }
        val title = Validators.nonEmpty(rawTitle, 2, 80).mapError { it as Failure }
        val description = Validators.nonEmpty(rawDescription, 4, 240).mapError { it as Failure }

        val localesResult = rawLocales.map { Validators.locale(it) }
        if (localesResult.any { it is Result.Err }) {
            val failure = (localesResult.first { it is Result.Err } as Result.Err).error
            return Result.err(failure)
        }
        val locales = localesResult.map { (it as Result.Ok).value }

        return when {
            id is Result.Err -> Result.err(id.error)
            title is Result.Err -> Result.err(title.error)
            description is Result.Err -> Result.err(description.error)
            locales.isEmpty() -> Result.err(ValidationFailure.Empty)
            else -> Result.ok(
                ContentPackDto(
                    id = (id as Result.Ok).value,
                    title = (title as Result.Ok).value,
                    description = (description as Result.Ok).value,
                    supportedLocales = locales
                )
            )
        }
    }
}

// Expect/Actual para utilidades de plataforma
public expect object IdGenerator {
    public fun uuid(): String
}

public expect object Clock {
    public fun nowIso(): String
}
