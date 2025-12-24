package com.pLg.core.domain.value

import com.pLg.core.domain.Result
import com.pLg.core.domain.ValidationFailure
import java.util.UUID

@JvmInline
value class UserId private constructor(val value: String) {
    companion object {
        fun create(raw: String): Result<UserId, ValidationFailure> {
            val trimmed = raw.trim()
            if (trimmed.isEmpty()) return Result.err(ValidationFailure.Empty)
            return try {
                UUID.fromString(trimmed)
                Result.ok(UserId(trimmed))
            } catch (_: IllegalArgumentException) {
                Result.err(ValidationFailure.InvalidFormat)
            }
        }

        fun new(): UserId = UserId(UUID.randomUUID().toString())
    }
}

@JvmInline
value class ChildId private constructor(val value: String) {
    companion object {
        fun create(raw: String): Result<ChildId, ValidationFailure> {
            val t = raw.trim()
            if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
            return try {
                UUID.fromString(t)
                Result.ok(ChildId(t))
            } catch (_: IllegalArgumentException) {
                Result.err(ValidationFailure.InvalidFormat)
            }
        }

        fun new(): ChildId = ChildId(UUID.randomUUID().toString())
    }
}

@JvmInline
value class ContentPackId private constructor(val value: String) {
    companion object {
        fun create(raw: String): Result<ContentPackId, ValidationFailure> {
            val t = raw.trim()
            if (t.isEmpty()) return Result.err(ValidationFailure.Empty)
            if (t.length > 64) return Result.err(ValidationFailure.TooLong)
            // allow kebab-case ids e.g., "numbers-pt"
            val regex = Regex("^[a-z0-9]+(?:[-_][a-z0-9]+)*\$")
            return if (regex.matches(t)) Result.ok(ContentPackId(t))
            else Result.err(ValidationFailure.InvalidFormat)
        }
    }
}

