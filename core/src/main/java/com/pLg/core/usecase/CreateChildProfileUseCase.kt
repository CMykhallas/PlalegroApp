package com.pLg.core.usecase

import com.pLg.core.domain.*
import com.pLg.core.domain.value.*

/**
 * Caso de uso para criar perfil de criança.
 */
class CreateChildProfileUseCase {
    operator fun invoke(rawDisplayName: String, rawAge: Int, rawLocale: String): Result<ChildProfile, Failure> {
        val name = NonEmptyString.create(rawDisplayName, minLen = 1, maxLen = 40).mapError { it as Failure }
        val age = Age.create(rawAge).mapError { it as Failure }
        val locale = LocaleCode.create(rawLocale).mapError { it as Failure }

        return when {
            name is Result.Err -> Result.err(name.error)
            age is Result.Err -> Result.err(age.error)
            locale is Result.Err -> Result.err(locale.error)
            else -> ChildProfile.create(
                id = ChildId.new(),
                displayName = (name as Result.Ok).value,
                age = (age as Result.Ok).value,
                preferredLocale = (locale as Result.Ok).value
            ).mapError { it as Failure }
        }
    }
}
