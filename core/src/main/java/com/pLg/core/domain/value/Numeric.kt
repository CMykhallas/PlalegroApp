package com.pLg.core.domain.value

import com.pLg.core.domain.Result
import com.pLg.core.domain.ValidationFailure

@JvmInline
value class Age private constructor(val value: Int) {
    companion object {
        fun create(raw: Int): Result<Age, ValidationFailure> {
            if (raw < 0) return Result.err(ValidationFailure.OutOfRange)
            if (raw > 18) return Result.err(ValidationFailure.OutOfRange)
            return Result.ok(Age(raw))
        }
    }
}

@JvmInline
value class PositiveInt private constructor(val value: Int) {
    companion object {
        fun create(raw: Int): Result<PositiveInt, ValidationFailure> {
            return if (raw > 0) Result.ok(PositiveInt(raw))
            else Result.err(ValidationFailure.OutOfRange)
        }
    }
}

@JvmInline
value class Percentage private constructor(val value: Int) {
    companion object {
        fun create(raw: Int): Result<Percentage, ValidationFailure> {
            return if (raw in 0..100) Result.ok(Percentage(raw))
            else Result.err(ValidationFailure.OutOfRange)
        }
    }
}
