package com.pLg.core.user

import com.pLg.core.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class UserFactoryTest {

    @Test
    fun createValidUser_returnsOk() {
        val now = Instant.now().toEpochMilli()
        val res = UserFactory.create("u1", "Ana", "ana@example.com", now)
        assertTrue(res is Result.Ok)
        val user = (res as Result.Ok).value
        assertEquals("u1", user.id)
    }

    @Test
    fun createInvalidUser_returnsErr() {
        val now = Instant.now().toEpochMilli()
        val res = UserFactory.create("", "Ana", "ana@example.com", now)
        assertTrue(res is Result.Err)
    }
}
