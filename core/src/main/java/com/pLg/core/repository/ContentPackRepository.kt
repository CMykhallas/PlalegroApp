package com.pLg.core.repository

import com.pLg.core.domain.ContentPack
import com.pLg.core.common.Result

interface ContentPackRepository {
    suspend fun save(pack: ContentPack): Result<Unit, Throwable>
    suspend fun findById(id: String): Result<ContentPack, Throwable>
    suspend fun findAll(): Result<List<ContentPack>, Throwable>
    suspend fun delete(id: String): Result<Unit, Throwable>
}
