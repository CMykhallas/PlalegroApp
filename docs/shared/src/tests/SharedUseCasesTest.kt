package com.pLg.shared.tests

import com.pLg.shared.domain.*
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SharedUseCasesTest {

    @Test
    fun `registerUser should return Ok for valid input`() {
        val result = UseCases.registerUser("Alice", "alice@example.com")
        assertTrue(result is Result.Ok)
        val user = (result as Result.Ok).value
        assertNotNull(user.id)
        assertEquals("Alice", user.name)
        assertEquals("alice@example.com", user.email)
    }

    @Test
    fun `registerUser should return Err for invalid email`() {
        val result = UseCases.registerUser("Bob", "invalid-email")
        assertTrue(result is Result.Err)
    }

    @Test
    fun `createChildProfile should validate locale and age`() {
        val res = UseCases.createChildProfile("Joao", 5, "pt-BR")
        assertTrue(res is Result.Ok)
        val child = (res as Result.Ok).value
        assertEquals("Joao", child.displayName)
        assertEquals(5, child.age)
        assertEquals("pt-BR", child.preferredLocale)
    }

    @Test
    fun `defineContentPack should accept multiple locales`() {
        val res = UseCases.defineContentPack(
            rawId = "numbers-pack",
            rawTitle = "Números",
            rawDescription = "Aprenda a contar até 10",
            rawLocales = listOf("pt-BR", "en", "es")
        )
        assertTrue(res is Result.Ok)
        val pack = (res as Result.Ok).value
        assertEquals("numbers-pack", pack.id)
        assertEquals(3, pack.supportedLocales.size)
    }

    @Test
    fun `defineContentPack should fail for invalid id`() {
        val res = UseCases.defineContentPack(
            rawId = "INVALID ID",
            rawTitle = "T",
            rawDescription = "Desc",
            rawLocales = listOf("pt-BR")
        )
        assertTrue(res is Result.Err)
    }
}
