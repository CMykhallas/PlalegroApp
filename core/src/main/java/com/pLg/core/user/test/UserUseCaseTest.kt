package com.pLg.core.user.usecase

import com.pLg.core.user.User
import com.pLg.core.util.Result
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

// Fake repository implementing all needed methods
class FakeUserRepo : UserRepository {
    private var stored: User? = User("u1", "Ana", "ana@example.com", Instant.now().toEpochMilli())

    override suspend fun getUserById(id: String): Result<User> {
        return stored?.takeIf { it.id == id }?.let { Result.Ok(it) }
            ?: Result.Err(com.pLg.core.util.DataError.NotFound())
    }

    override suspend fun updateUser(user: User): Result<User> {
        stored = user
        return Result.Ok(user)
    }

    override suspend fun deleteUser(id: String): Result<Unit> {
        return if (stored?.id == id) {
            stored = null
            Result.Ok(Unit)
        } else {
            Result.Err(com.pLg.core.util.DataError.NotFound())
        }
    }
}

class UserUseCaseTest {

    private val repo = FakeUserRepo()

    @Test
    fun createUser_valid() {
        val now = Instant.now().toEpochMilli()
        val useCase = CreateUser()
        val res = useCase("u2", "Bruno", "bruno@example.com", now)
        assertTrue(res is Result.Ok)
    }

    @Test
    fun getUserProfile_existing() = runBlocking {
        val useCase = GetUserProfile(repo)
        val res = useCase("u1")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun updateUserProfile_changesName() = runBlocking {
        val useCase = UpdateUserProfile(repo)
        val res = useCase("u1", newName = "Ana Maria")
        assertTrue(res is Result.Ok)
        val user = (res as Result.Ok).value
        assertEquals("Ana Maria", user.name)
    }

    @Test
    fun deleteUser_existing() = runBlocking {
        val useCase = DeleteUser(repo)
        val res = useCase("u1")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun deleteUser_nonExisting() = runBlocking {
        val useCase = DeleteUser(repo)
        val res = useCase("uX")
        assertTrue(res is Result.Err)
    }
}
