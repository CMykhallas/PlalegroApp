package com.pLg.core.i18n

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class I18nTests {

    private val catalog = InMemoryMessageCatalog(
        mapOf(
            "en" to mapOf(
                "greeting.hello" to "Hello",
                "amount.items" to "{count, plural, =0 {No items} one {# item} other {# items}}",
                "status.message" to "{status, select, ok {Ready} fail {Error} other {Unknown}}"
            )
        )
    )
    private val localeProvider = MutableLocaleProvider(LocaleSpec("en"))
    private val i18n = I18n(catalog, localeProvider)

    @Test
    fun testSimpleMessage() {
        assertEquals("Hello", i18n.t("greeting.hello"))
    }

    @Test
    fun testPluralZero() {
        assertEquals("No items", i18n.t("amount.items", mapOf("count" to 0)))
    }

    @Test
    fun testPluralOne() {
        assertEquals("1 item", i18n.t("amount.items", mapOf("count" to 1)))
    }

    @Test
    fun testPluralOther() {
        assertEquals("5 items", i18n.t("amount.items", mapOf("count" to 5)))
    }

    @Test
    fun testSelectOk() {
        assertEquals("Ready", i18n.t("status.message", mapOf("status" to "ok")))
    }

    @Test
    fun testSelectOther() {
        assertEquals("Unknown", i18n.t("status.message", mapOf("status" to "foo")))
    }

    @Test
    fun testFormatters() {
        val formatted = i18n.currency(123.45, "USD")
        assertTrue(formatted.contains("123"))
        val date = i18n.dateMedium(LocalDate.of(2025, 12, 24))
        assertTrue(date.contains("2025"))
    }
}
