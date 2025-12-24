package com.pLg.core.domain

import com.pLg.core.domain.value.Email
import com.pLg.core.domain.value.NonEmptyString
import com.pLg.core.domain.value.UserId

/**
 * Represents a guardian/educator account.
 */
data class User private constructor(
    val id: UserId,
    val name: NonEmptyString,
    val email: Email
) {
    companion object {
        fun create(id: UserId, name: NonEmptyString, email: Email): Result<User, ValidationFailure> {
            // All value objects are validated at creation; here we just compose.
            return Result.ok(User(id, name, email))
        }
    }
}
