package com.pLg.core.user.usecase

import com.pLg.core.user.User
import com.pLg.core.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlinx.coroutines.runBlocking
import java.time.Instant

class FakeUpdateRepo : UserRepository {
    private var stored = User("u1", "Ana", "ana@example.com", Instant.now().toEpochMilli())

    override suspend fun getUserById(id: String): Result<User> =
        if (id == stored.id) Result.Ok(stored) else Result.Err(com.pLg.core.util.DataError.NotFound())

    override suspend fun updateUser(user: User): Result<User> {
        stored = user
        return Result.Ok(stored)
    }
}

class UpdateUserProfileTest {

    private val repo = FakeUpdateRepo()
    private val useCase = UpdateUserProfile(repo)

    @Test
    fun updateName_success() = runBlocking {
        val res = useCase("u1", newName = "Ana Maria")
        assertTrue(res is Result.Ok)
        val user = (res as Result.Ok).value
        assertEquals("Ana Maria", user.name)
    }

    @Test
    fun updateInvalidId_returnsErr() = runBlocking {
        val res = useCase("", newName = "Ana")
        assertTrue(res is Result.Err)
    }
}
