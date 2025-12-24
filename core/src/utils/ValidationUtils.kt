package com.pLg.core.utils

import com.pLg.core.domain.Result
import com.pLg.core.domain.ValidationFailure

object ValidationUtils {
    fun requireNonEmpty(raw: String, minLen: Int = 1, maxLen: Int = 256): Result<String, ValidationFailure> {
        val t = raw.trim()
        if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
        if (t.length < minLen) return Result.err(ValidationFailure.TooShort)
        if (t.length > maxLen) return Result.err(ValidationFailure.TooLong)
        return Result.ok(t)
    }

    fun requireRegex(raw: String, regex: Regex): Result<String, ValidationFailure> {
        val t = raw.trim()
        if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
        return if (regex.matches(t)) Result.ok(t) else Result.err(ValidationFailure.InvalidFormat)
    }

    fun requireRange(raw: Int, min: Int, max: Int): Result<Int, ValidationFailure> {
        return if (raw in min..max) Result.ok(raw) else Result.err(ValidationFailure.OutOfRange)
    }
}
