package org.playlearn.data.repo

import org.playlearn.core.model.Preferences
import org.playlearn.core.util.AppResult
import org.playlearn.data.local.PreferencesDao
import org.playlearn.domain.repo.PreferencesRepository

class PreferencesRepositoryImpl(
    private val preferencesDao: PreferencesDao
) : PreferencesRepository {

    override suspend fun get(): AppResult<Preferences> = try {
        val entity = preferencesDao.get()
        if (entity == null) {
            val default = Mapper.toEntity(Preferences())
            preferencesDao.upsert(default)
            AppResult.Success(Mapper.fromEntity(default))
        } else {
            AppResult.Success(Mapper.fromEntity(entity))
        }
    } catch (t: Throwable) {
        AppResult.Error(t)
    }

    override suspend fun update(prefs: Preferences): AppResult<Unit> = try {
        preferencesDao.upsert(Mapper.toEntity(prefs))
        AppResult.Success(Unit)
    } catch (t: Throwable) {
        AppResult.Error(t)
    }
}
