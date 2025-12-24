package com.pLg.core.domain.value

import com.pLg.core.domain.Result
import com.pLg.core.domain.ValidationFailure

@JvmInline
value class NonEmptyString private constructor(val value: String) {
    companion object {
        fun create(raw: String, minLen: Int = 1, maxLen: Int = 256): Result<NonEmptyString, ValidationFailure> {
            val t = raw.trim()
            if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
            if (t.length < minLen) return Result.err(ValidationFailure.TooShort)
            if (t.length > maxLen) return Result.err(ValidationFailure.TooLong)
            return Result.ok(NonEmptyString(t))
        }
    }
}

@JvmInline
value class Email private constructor(val value: String) {
    companion object {
        private val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        fun create(raw: String): Result<Email, ValidationFailure> {
            val t = raw.trim()
            if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
            return if (regex.matches(t)) Result.ok(Email(t))
            else Result.err(ValidationFailure.InvalidFormat)
        }
    }
}

@JvmInline
value class LocaleCode private constructor(val value: String) {
    companion object {
        // ISO-like: en, pt, en-US, pt-BR
        private val regex = Regex("^[a-z]{2}(?:-[A-Z]{2})?\$")
        fun create(raw: String): Result<LocaleCode, ValidationFailure> {
            val t = raw.trim()
            if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
            return if (regex.matches(t)) Result.ok(LocaleCode(t))
            else Result.err(ValidationFailure.InvalidFormat)
        }
    }
}
