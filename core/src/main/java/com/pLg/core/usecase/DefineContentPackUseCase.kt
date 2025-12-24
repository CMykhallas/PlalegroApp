package com.pLg.core.usecase

import com.pLg.core.domain.*
import com.pLg.core.domain.value.*

/**
 * Caso de uso para definir metadados de um pacote de conteúdo educativo.
 */
class DefineContentPackUseCase {
    operator fun invoke(rawId: String, rawTitle: String, rawDescription: String, rawLocales: List<String>): Result<ContentPack, Failure> {
        val id = ContentPackId.create(rawId).mapError { it as Failure }
        val title = NonEmptyString.create(rawTitle, minLen = 2, maxLen = 80).mapError { it as Failure }
        val description = NonEmptyString.create(rawDescription, minLen = 4, maxLen = 240).mapError { it as Failure }

        val localesResult = rawLocales.map { LocaleCode.create(it) }
        if (localesResult.any { it is Result.Err }) {
            val firstError = (localesResult.first { it is Result.Err } as Result.Err).error
            return Result.err(firstError)
        }
        val locales = localesResult.map { (it as Result.Ok).value }.toSet()

        return when {
            id is Result.Err -> Result.err(id.error)
            title is Result.Err -> Result.err(title.error)
            description is Result.Err -> Result.err(description.error)
            locales.isEmpty() -> Result.err(ValidationFailure.Empty)
            else -> ContentPack.create(
                (id as Result.Ok).value,
                (title as Result.Ok).value,
                (description as Result.Ok).value,
                locales
            ).mapError { it as Failure }
        }
    }
}
