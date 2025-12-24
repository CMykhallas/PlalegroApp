package com.pLg.core.domain

import com.pLg.core.domain.value.ContentPackId
import com.pLg.core.domain.value.LocaleCode
import com.pLg.core.domain.value.NonEmptyString

/**
 * Educational content pack metadata.
 */
data class ContentPack private constructor(
    val id: ContentPackId,
    val title: NonEmptyString,
    val description: NonEmptyString,
    val supportedLocales: Set<LocaleCode>
) {
    companion object {
        fun create(
            id: ContentPackId,
            title: NonEmptyString,
            description: NonEmptyString,
            supportedLocales: Set<LocaleCode>
        ): Result<ContentPack, ValidationFailure> {
            if (supportedLocales.isEmpty()) return Result.err(ValidationFailure.Empty)
            return Result.ok(ContentPack(id, title, description, supportedLocales))
        }
    }
}
