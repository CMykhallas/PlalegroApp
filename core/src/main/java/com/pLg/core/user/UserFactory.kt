package com.pLg.core.user

import com.pLg.core.util.DataError
import com.pLg.core.util.Result

/**
 * Factory helpers for safe creation of User.
 * Wraps validation errors in Result.Err instead of throwing.
 */
object UserFactory {
    fun create(
        id: String,
        name: String,
        email: String,
        createdAtEpochMillis: Long
    ): Result<User> {
        return try {
            Result.Ok(User(id.trim(), name.trim(), email.lowercase(), createdAtEpochMillis))
        } catch (e: IllegalArgumentException) {
            Result.Err(DataError.Validation(e.message ?: "Invalid user"))
        }
    }
}
