package com.pLg.core.user.usecase

import com.pLg.core.user.User
import com.pLg.core.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlinx.coroutines.runBlocking
import java.time.Instant

class FakeUserRepository : UserRepository {
    override suspend fun getUserById(id: String): Result<User> {
        return if (id == "u1") {
            Result.Ok(User("u1", "Ana", "ana@example.com", Instant.now().toEpochMilli()))
        } else {
            Result.Err(com.pLg.core.util.DataError.NotFound())
        }
    }
}

class GetUserProfileTest {

    private val repo = FakeUserRepository()
    private val useCase = GetUserProfile(repo)

    @Test
    fun getExistingUser_returnsOk() = runBlocking {
        val res = useCase("u1")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun getNonExistingUser_returnsErr() = runBlocking {
        val res = useCase("u2")
        assertTrue(res is Result.Err)
    }
}
