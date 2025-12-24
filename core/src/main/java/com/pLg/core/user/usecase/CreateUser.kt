package com.pLg.core.user.usecase

import com.pLg.core.user.User
import com.pLg.core.user.UserFactory
import com.pLg.core.util.Result
import com.pLg.core.util.DataError

/**
 * Use case: create/register a new user.
 * Validates input and returns a User domain object.
 */
class CreateUser {

    operator fun invoke(
        id: String,
        name: String,
        email: String,
        createdAtEpochMillis: Long
    ): Result<User> {
        if (id.isBlank() || name.isBlank() || email.isBlank()) {
            return Result.Err(DataError.Validation("All fields must be provided"))
        }
        return UserFactory.create(id, name, email, createdAtEpochMillis)
    }
}
