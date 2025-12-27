package org.playlearn.domain.repo

import org.playlearn.core.model.User
import org.playlearn.core.util.AppResult

interface UserRepository {
    suspend fun register(user: User): AppResult<User>
    suspend fun currentUser(): AppResult<User?>
    suspend fun getUserById(id: String): AppResult<User>
    suspend fun updatePreferences(prefsMap: Map<String, Any>): AppResult<Unit>
}
