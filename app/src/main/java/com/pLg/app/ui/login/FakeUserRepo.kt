package com.pLg.app.ui.login

import com.pLg.data.repo.contract.UserRepository
import com.pLg.core.model.User

class FakeUserRepo : UserRepository {
    private var user: User? = null
    private val prefs: MutableMap<String, Any> = mutableMapOf()

    override suspend fun register(user: User): Result<User> = runCatching {
        this.user = user
        user
    }

    override suspend fun currentUser(): Result<User?> = runCatching { user }

    override suspend fun getUserById(id: String): Result<User> =
        runCatching { user ?: throw IllegalStateException("User not found") }

    override suspend fun updatePreferences(prefsMap: Map<String, Any>): Result<Unit> =
        runCatching { prefs.putAll(prefsMap) }
}
