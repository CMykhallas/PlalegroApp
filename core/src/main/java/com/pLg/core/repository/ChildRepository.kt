package com.pLg.core.repository

import com.pLg.core.domain.ChildProfile
import com.pLg.core.common.Result

interface ChildRepository {
    suspend fun save(child: ChildProfile): Result<Unit, Throwable>
    suspend fun findById(id: String): Result<ChildProfile, Throwable>
    suspend fun findAll(): Result<List<ChildProfile>, Throwable>
    suspend fun delete(id: String): Result<Unit, Throwable>
}
