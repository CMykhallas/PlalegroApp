package com.pLg.data.test

import com.pLg.data.repo.UserRepository
import com.pLg.data.source.CachePolicy
import com.pLg.data.source.local.InMemoryUserDao
import com.pLg.data.source.remote.ApiClient
import com.pLg.data.source.remote.Response
import com.pLg.core.user.User
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FakeApiClient(private val handler: (String) -> Response): ApiClient {
    override suspend fun get(path: String, query: Map<String, String>): Response = handler(path)
    override suspend fun post(path: String, body: String) = Response(200, "")
    override suspend fun put(path: String, body: String) = Response(200, "")
    override suspend fun delete(path: String) = Response(200, "")
}

class UserRepositoryTest {
    @Test
    fun networkFirst_success() = runBlocking {
        val api = FakeApiClient { path ->
            Response(200, """{"id":"u1","name":"Ana","email":"ana@example.com","createdAtIso":"2025-12-24T09:50:00Z"}""")
        }
        val dao = InMemoryUserDao()
        val repo = UserRepository(api, dao)

        val res = repo.getUser("u1", CachePolicy.NetworkFirst)
        val user = (res as com.pLg.data.util.Result.Ok<User>).value
        assertEquals("u1", user.id)
        assertEquals("ana@example.com", user.email)
    }
}
