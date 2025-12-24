package com.pLg.core.user.usecase

import com.pLg.core.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class CreateUserTest {

    private val useCase = CreateUser()

    @Test
    fun createValidUser_returnsOk() {
        val now = Instant.now().toEpochMilli()
        val res = useCase("u1", "Ana", "ana@example.com", now)
        assertTrue(res is Result.Ok)
    }

    @Test
    fun createInvalidUser_returnsErr() {
        val now = Instant.now().toEpochMilli()
        val res = useCase("", "Ana", "ana@example.com", now)
        assertTrue(res is Result.Err)
    }
}
