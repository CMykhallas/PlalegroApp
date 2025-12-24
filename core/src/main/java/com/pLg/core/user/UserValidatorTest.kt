package com.pLg.core.user

import com.pLg.core.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class UserValidatorTest {

    @Test
    fun validateId_valid() {
        val res = UserValidator.validateId("u1")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun validateId_blank() {
        val res = UserValidator.validateId("")
        assertTrue(res is Result.Err)
    }

    @Test
    fun validateName_valid() {
        val res = UserValidator.validateName("Ana")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun validateName_tooLong() {
        val res = UserValidator.validateName("A".repeat(60))
        assertTrue(res is Result.Err)
    }

    @Test
    fun validateEmail_valid() {
        val res = UserValidator.validateEmail("ana@example.com")
        assertTrue(res is Result.Ok)
    }

    @Test
    fun validateEmail_invalid() {
        val res = UserValidator.validateEmail("anaexample.com")
        assertTrue(res is Result.Err)
    }

    @Test
    fun validateWholeUser_valid() {
        val now = Instant.now().toEpochMilli()
        val user = User("u1", "Ana", "ana@example.com", now)
        val res = UserValidator.validate(user)
        assertTrue(res is Result.Ok)
    }
}
