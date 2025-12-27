package org.playlearn.data.repo

import org.playlearn.core.model.User
import org.playlearn.core.util.AppResult
import org.playlearn.data.local.UserDao
import org.playlearn.data.remote.ApiService
import org.playlearn.domain.repo.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val api: ApiService
) : UserRepository {

    override suspend fun register(user: User): AppResult<User> = try {
        val registered = api.register(user)
        userDao.upsertUser(Mapper.toEntity(registered))
        AppResult.Success(registered)
    } catch (t: Throwable) {
        AppResult.Error(t)
    }

    override suspend fun currentUser(): AppResult<User?> = try {
        AppResult.Success(userDao.getCurrentUser()?.let(Mapper::fromEntity))
    } catch (t: Throwable) {
        AppResult.Error(t)
    }

    override suspend fun getUserById(id: String): AppResult<User> = try {
        val remote = api.getUser(id)
        userDao.upsertUser(Mapper.toEntity(remote))
        AppResult.Success(remote)
    } catch (t: Throwable) {
        AppResult.Error(t)
    }

    override suspend fun updatePreferences(prefsMap: Map<String, Any>): AppResult<Unit> {
        // In a real app, merge into PreferencesEntity; simplified here
        return AppResult.Success(Unit)
    }
}
