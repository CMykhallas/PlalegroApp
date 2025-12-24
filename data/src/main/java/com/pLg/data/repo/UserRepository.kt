package com.pLg.data.repo

import com.pLg.data.model.mapper.UserMapper
import com.pLg.data.source.CachePolicy
import com.pLg.data.source.local.UserDao
import com.pLg.data.source.remote.ApiClient
import com.pLg.data.source.remote.UserApi
import com.pLg.data.util.Result
import com.pLg.data.util.retry
import com.pLg.data.util.RetryPolicy

class UserRepository(
    private val api: ApiClient,
    private val dao: UserDao,
    private val retryPolicy: RetryPolicy = RetryPolicy()
) {

    suspend fun getUser(
        id: String,
        policy: CachePolicy = CachePolicy.NetworkFirst
    ): Result<com.pLg.core.user.User> { // depende de um domain model (ex.: com.pLg.core.user.User)
        return when (policy) {
            CachePolicy.CacheOnly -> {
                when (val local = dao.getById(id)) {
                    is Result.Ok -> local.value?.let { Result.Ok(it.toDomain()) }
                        ?: Result.Err(com.pLg.data.util.DataError.NotFound())
                    is Result.Err -> local
                }
            }
            CachePolicy.NetworkOnly -> fromNetwork(id, persist = true)
            CachePolicy.CacheFirst -> {
                val local = dao.getById(id)
                if (local is Result.Ok && local.value != null) {
                    Result.Ok(local.value.toDomain()).also {
                        // atualiza em background (chamar de fora)
                    }
                } else {
                    fromNetwork(id, persist = true)
                }
            }
            CachePolicy.NetworkFirst -> {
                val net = fromNetwork(id, persist = true)
                if (net is Result.Ok) net else {
                    val local = dao.getById(id)
                    if (local is Result.Ok && local.value != null) Result.Ok(local.value.toDomain()) else net
                }
            }
        }
    }

    private suspend fun fromNetwork(id: String, persist: Boolean): Result<com.pLg.core.user.User> {
        val res = try {
            retry(retryPolicy) { UserApi.getUser(api, id) }
        } catch (t: Throwable) {
            return Result.Err(com.pLg.data.util.DataError.Network("Network retry exhausted", t))
        }
        return when (res) {
            is Result.Ok -> {
                val mapped = UserMapper.dtoToEntity(res.value)
                when (mapped) {
                    is Result.Ok -> {
                        if (persist) dao.upsert(mapped.value)
                        Result.Ok(mapped.value.toDomain())
                    }
                    is Result.Err -> mapped
                }
            }
            is Result.Err -> res
        }
    }
}

// Extensão simples para converter entity -> domain
private fun com.pLg.data.model.entity.UserEntity.toDomain(): com.pLg.core.user.User =
    com.pLg.core.user.User(id = id, name = name, email = email, createdAtEpochMillis = createdAtEpochMillis)
