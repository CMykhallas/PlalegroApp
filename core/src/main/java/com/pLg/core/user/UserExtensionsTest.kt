package com.pLg.core.user

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class UserExtensionsTest {

    private val now = Instant.now().toEpochMilli()

    private val users = listOf(
        User("u1", "Ana", "ana@example.com", now - 1000),
        User("u2", "Bruno", "bruno@example.com", now),
        User("u3", "Carla", "carla@example.com", now - 500)
    )

    @Test
    fun sortedByCreated_ordersCorrectly() {
        val sorted = users.sortedByCreated()
        assertEquals("u1", sorted.first().id)
        assertEquals("u2", sorted.last().id)
    }

    @Test
    fun findByEmail_caseInsensitive() {
        val found = users.findByEmail("BRUNO@example.com")
        assertNotNull(found)
        assertEquals("u2", found!!.id)
    }

    @Test
    fun names_returnsListOfNames() {
        val names = users.names()
        assertEquals(listOf("Ana", "Bruno", "Carla"), names)
    }

    @Test
    fun anonymized_replacesEmails() {
        val anon = users.anonymized()
        assertTrue(anon.all { it.email == "hidden@example.com" })
    }
}
