package org.playlearn.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.playlearn.core.model.Content
import org.playlearn.core.util.AppResult
import org.playlearn.data.local.ContentDao
import org.playlearn.data.remote.ApiService
import org.playlearn.domain.repo.ContentRepository

class ContentRepositoryImpl(
    private val contentDao: ContentDao,
    private val api: ApiService
) : ContentRepository {

    override fun observeContent(): Flow<List<Content>> = flow {
        val cached = contentDao.getAll().map(Mapper::fromEntity)
        emit(cached)
        val remote = api.fetchContents()
        contentDao.upsertAll(remote.map(Mapper::toEntity))
        emit(remote)
    }

    override suspend fun refreshContentPacks(): AppResult<Unit> = try {
        val remote = api.fetchContents()
        contentDao.upsertAll(remote.map(Mapper::toEntity))
        AppResult.Success(Unit)
    } catch (t: Throwable) {
        AppResult.Error(t)
    }

    override suspend fun getContentById(id: String): AppResult<Content> = try {
        val local = contentDao.getById(id)?.let(Mapper::fromEntity)
        if (local != null) AppResult.Success(local) else AppResult.Error(NoSuchElementException("Not found"))
    } catch (t: Throwable) {
        AppResult.Error(t)
    }
}
