package org.playlearn.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.playlearn.core.model.Content
import org.playlearn.domain.repo.ContentRepository
import javax.inject.Inject

class GetContentsUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(): Flow<List<Content>> = contentRepository.observeContent()
    suspend fun refresh() = contentRepository.refreshContentPacks()
}
