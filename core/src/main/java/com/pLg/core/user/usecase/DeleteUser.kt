package com.pLg.core.user.usecase

import com.pLg.core.util.Result
import com.pLg.core.util.DataError

/**
 * Use case: remove a user by id.
 */
class DeleteUser(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        if (id.isBlank()) return Result.Err(DataError.Validation("User id must not be blank"))
        return repository.deleteUser(id)
    }
}

/**
 * Repository abstraction for delete.
 */
interface UserRepository {
    suspend fun deleteUser(id: String): Result<Unit>
}
