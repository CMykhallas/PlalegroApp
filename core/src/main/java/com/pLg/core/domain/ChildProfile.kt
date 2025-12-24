package com.pLg.core.domain

import com.pLg.core.domain.value.Age
import com.pLg.core.domain.value.ChildId
import com.pLg.core.domain.value.LocaleCode
import com.pLg.core.domain.value.NonEmptyString

/**
 * Child profile for adaptive learning and personalization.
 */
data class ChildProfile private constructor(
    val id: ChildId,
    val displayName: NonEmptyString,
    val age: Age,
    val preferredLocale: LocaleCode
) {
    companion object {
        fun create(
            id: ChildId,
            displayName: NonEmptyString,
            age: Age,
            preferredLocale: LocaleCode
        ): Result<ChildProfile, ValidationFailure> {
            return Result.ok(ChildProfile(id, displayName, age, preferredLocale))
        }
    }
}
