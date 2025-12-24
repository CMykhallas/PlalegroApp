package com.pLg.core.user.usecase

import com.pLg.core.user.User
import com.pLg.core.user.UserValidator
import com.pLg.core.util.Result
import com.pLg.core.util.DataError

/**
 * Use case: update name/email/avatar of a user.
 */
class UpdateUserProfile(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        id: String,
        newName: String? = null,
        newEmail: String? = null,
        newAvatar: String? = null
    ): Result<User> {
        if (id.isBlank()) return Result.Err(DataError.Validation("User id must not be blank"))

        val current = repository.getUserById(id)
        if (current is Result.Err) return current

        val user = (current as Result.Ok).value
        val updated = user.copy(
            name = newName?.trim() ?: user.name,
            email = newEmail?.lowercase() ?: user.email
            // avatar would be handled in persistence layer
        )

        val validated = UserValidator.validate(updated)
        return when (validated) {
            is Result.Ok -> repository.updateUser(validated.value)
            is Result.Err -> validated
        }
    }
}

/**
 * Repository abstraction for update.
 */
interface UserRepository {
    suspend fun getUserById(id: String): Result<User>
    suspend fun updateUser(user: User): Result<User>
}
