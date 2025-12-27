package org.playlearn.domain.repo

import kotlinx.coroutines.flow.Flow
import org.playlearn.core.model.Content
import org.playlearn.core.util.AppResult

interface ContentRepository {
    fun observeContent(): Flow<List<Content>>
    suspend fun refreshContentPacks(): AppResult<Unit>
    suspend fun getContentById(id: String): AppResult<Content>
}
