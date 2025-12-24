package com.pLg.core.user.usecase

import com.pLg.core.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlinx.coroutines.runBlocking

class FakeDeleteRepo : UserRepository {
    private val users = mutableSetOf("u1")

    override suspend fun deleteUser(id: String): Result<Unit> {
        return if (users.remove(id)) Result.Ok(Unit)
        else Result.Err(com.pLg.core.util.DataError.NotFound())
    }
}

class DeleteUserTest {

    private val repo = FakeDeleteRepo()
    private val useCase = DeleteUser(repo)

    @Test
    fun deleteExistingUser_returnsOk() = runBlocking {
        val res = useCase("u1")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun deleteNonExistingUser_returnsErr() = runBlocking {
        val res = useCase("u2")
        assertTrue(res is Result.Err)
    }
}
