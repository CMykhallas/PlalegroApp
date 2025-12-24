package com.pLg.core.user

import com.pLg.core.util.DataError
import com.pLg.core.util.Result
import java.time.Instant

/**
 * Domain model for a child user profile.
 * Immutable, validated, and serialization-free.
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val createdAtEpochMillis: Long
) {
    init {
        require(id.isNotBlank()) { "User id must not be blank" }
        require(name.isNotBlank()) { "User name must not be blank" }
        require(email.contains("@")) { "Invalid email format" }
    }

    val createdAtInstant: Instant get() = Instant.ofEpochMilli(createdAtEpochMillis)

    fun anonymized(): User = copy(email = "hidden@example.com")

    fun initials(): String = name
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.first().uppercaseChar() }
        .joinToString("")
}

/**
 * Factory helpers for safe creation.
 */
object UserFactory {
    fun create(id: String, name: String, email: String, createdAtEpochMillis: Long): Result<User> {
        return try {
            Result.Ok(User(id, name.trim(), email.lowercase(), createdAtEpochMillis))
        } catch (e: IllegalArgumentException) {
            Result.Err(DataError.Validation(e.message ?: "Invalid user"))
        }
    }
}

/**
 * Extensions for domain logic.
 */
fun List<User>.sortedByCreated(): List<User> = this.sortedBy { it.createdAtEpochMillis }

fun List<User>.findByEmail(email: String): User? =
    this.firstOrNull { it.email.equals(email, ignoreCase = true) }
