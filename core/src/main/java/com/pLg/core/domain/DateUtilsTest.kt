package com.pLg.core.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DateUtilsTest {
    @Test
    fun `should format and parse date correctly`() {
        val today = LocalDate.of(2025, 12, 23)
        val formatted = DateUtils.formatDate(today)
        val parsed = DateUtils.parseDate(formatted)

        assertEquals(today, parsed)
    }

    @Test
    fun `should return null for invalid date`() {
        val parsed = DateUtils.parseDate("invalid-date")
        assertNull(parsed)
    }
}
