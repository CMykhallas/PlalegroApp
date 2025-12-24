package com.pLg.data.source.local

import com.pLg.data.model.entity.UserEntity
import com.pLg.data.util.Result

interface UserDao {
    fun getById(id: String): Result<UserEntity?>
    fun upsert(user: UserEntity): Result<Unit>
    fun delete(id: String): Result<Unit>
}

class InMemoryUserDao : UserDao {
    private val data = mutableMapOf<String, UserEntity>()

    override fun getById(id: String): Result<UserEntity?> = safeLocal { data[id] }
    override fun upsert(user: UserEntity): Result<Unit> = safeLocal { data[user.id] = user }
    override fun delete(id: String): Result<Unit> = safeLocal { data.remove(id); Unit }
}
