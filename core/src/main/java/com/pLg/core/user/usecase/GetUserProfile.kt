package com.pLg.core.user.usecase

import com.pLg.core.user.User
import com.pLg.core.util.Result
import com.pLg.core.util.DataError

/**
 * Use case: fetch user profile by id.
 * In a real app, this would query a repository.
 */
class GetUserProfile(
    private val repository: UserRepository // abstraction to be provided
) {
    suspend operator fun invoke(id: String): Result<User> {
        if (id.isBlank()) return Result.Err(DataError.Validation("User id must not be blank"))
        return repository.getUserById(id)
    }
}

/**
 * Repository abstraction for decoupling.
 */
interface UserRepository {
    suspend fun getUserById(id: String): Result<User>
}
