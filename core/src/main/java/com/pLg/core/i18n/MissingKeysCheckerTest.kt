package com.pLg.core.i18n

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MissingKeysCheckerTest {

    @Test
    fun testMissingKeys() {
        val catalog = InMemoryMessageCatalog(
            mapOf(
                "en" to mapOf("greeting.hello" to "Hello", "bye" to "Bye"),
                "pt" to mapOf("greeting.hello" to "Olá")
            )
        )
        val missing = MissingKeysChecker.check(catalog)
        assertTrue(missing["pt"]!!.contains("bye"))
    }
}
