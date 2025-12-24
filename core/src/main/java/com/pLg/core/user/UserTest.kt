package com.pLg.core.user

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class UserTest {

    @Test
    fun validUser_initialization() {
        val now = Instant.now().toEpochMilli()
        val user = User("u1", "Ana Maria", "ana@example.com", now)
        assertEquals("u1", user.id)
        assertEquals("Ana Maria", user.name)
        assertEquals("ana@example.com", user.email)
        assertEquals(now, user.createdAtEpochMillis)
    }

    @Test
    fun invalidUser_blankIdThrows() {
        val now = Instant.now().toEpochMilli()
        assertThrows(IllegalArgumentException::class.java) {
            User("", "Ana", "ana@example.com", now)
        }
    }

    @Test
    fun anonymizedUser_hidesEmail() {
        val user = User("u1", "Ana", "ana@example.com", Instant.now().toEpochMilli())
        val anon = user.anonymized()
        assertEquals("hidden@example.com", anon.email)
    }

    @Test
    fun initials_areComputedCorrectly() {
        val user = User("u1", "Ana Maria", "ana@example.com", Instant.now().toEpochMilli())
        assertEquals("AM", user.initials())
    }
}
