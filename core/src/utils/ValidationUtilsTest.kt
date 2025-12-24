package com.pLg.core.utils

import com.pLg.core.domain.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidationUtilsTest {
    @Test
    fun `should validate non-empty string`() {
        val result = ValidationUtils.requireNonEmpty("Hello", 1, 10)
        assertTrue(result is Result.Ok)
    }

    @Test
    fun `should fail for empty string`() {
        val result = ValidationUtils.requireNonEmpty("", 1, 10)
        assertTrue(result is Result.Err)
    }

    @Test
    fun `should validate regex`() {
        val result = ValidationUtils.requireRegex("abc123", Regex("^[a-z0-9]+$"))
        assertTrue(result is Result.Ok)
    }

    @Test
    fun `should fail regex`() {
        val result = ValidationUtils.requireRegex("ABC!", Regex("^[a-z0-9]+$"))
        assertTrue(result is Result.Err)
    }
}
