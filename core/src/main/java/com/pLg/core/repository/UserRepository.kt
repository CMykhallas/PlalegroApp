package com.pLg.core.repository

import com.pLg.core.domain.User
import com.pLg.core.common.Result

interface UserRepository {
    suspend fun save(user: User): Result<Unit, Throwable>
    suspend fun findById(id: String): Result<User, Throwable>
    suspend fun findAll(): Result<List<User>, Throwable>
    suspend fun delete(id: String): Result<Unit, Throwable>
}
