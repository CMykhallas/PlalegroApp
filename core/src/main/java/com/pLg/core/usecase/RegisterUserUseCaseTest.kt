package com.pLg.core.usecase

import com.pLg.core.domain.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegisterUserUseCaseTest {
    private val useCase = RegisterUserUseCase()

    @Test
    fun `should register user with valid data`() {
        val result = useCase("Maria", "maria@example.com")
        assertTrue(result is Result.Ok)
    }

    @Test
    fun `should fail with empty name`() {
        val result = useCase("", "maria@example.com")
        assertTrue(result is Result.Err)
    }

    @Test
    fun `should fail with invalid email`() {
        val result = useCase("Carlos", "invalid-email")
        assertTrue(result is Result.Err)
    }
}
