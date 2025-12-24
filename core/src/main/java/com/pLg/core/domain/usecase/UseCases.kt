package com.pLg.core.domain.usecase

import com.pLg.core.domain.*
import com.pLg.core.domain.value.*

/**
 * Pure use cases orchestrating validated value objects and entities.
 */
object UseCases {

    fun registerUser(
        rawName: String,
        rawEmail: String
    ): Result<User, Failure> {
        val name = NonEmptyString.create(rawName, minLen = 2, maxLen = 64).mapError { it as Failure }
        val email = Email.create(rawEmail).mapError { it as Failure }

        return when {
            name is Result.Err -> Result.err(name.error)
            email is Result.Err -> Result.err(email.error)
            else -> {
                val id = UserId.new()
                val user = User.create(id, (name as Result.Ok).value, (email as Result.Ok).value)
                    .mapError { it as Failure }
                user
            }
        }
    }

    fun createChildProfile(
        rawDisplayName: String,
        rawAge: Int,
        rawLocale: String
    ): Result<ChildProfile, Failure> {
        val name = NonEmptyString.create(rawDisplayName, minLen = 1, maxLen = 40).mapError { it as Failure }
        val age = Age.create(rawAge).mapError { it as Failure }
        val locale = LocaleCode.create(rawLocale).mapError { it as Failure }

        return when {
            name is Result.Err -> Result.err(name.error)
            age is Result.Err -> Result.err(age.error)
            locale is Result.Err -> Result.err(locale.error)
            else -> {
                val profile = ChildProfile.create(
                    id = ChildId.new(),
                    displayName = (name as Result.Ok).value,
                    age = (age as Result.Ok).value,
                    preferredLocale = (locale as Result.Ok).value
                ).mapError { it as Failure }
                profile
            }
        }
    }

    fun defineContentPack(
        rawId: String,
        rawTitle: String,
        rawDescription: String,
        rawLocales: List<String>
    ): Result<ContentPack, Failure> {
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

    fun startSession(childIdRaw: String, packIdRaw: String): Result<GameSession, Failure> {
        val childId = ChildId.create(childIdRaw).mapError { it as Failure }
        val packId = ContentPackId.create(packIdRaw).mapError { it as Failure }

        return when {
            childId is Result.Err -> Result.err(childId.error)
            packId is Result.Err -> Result.err(packId.error)
            else -> GameSession.start((childId as Result.Ok).value, (packId as Result.Ok).value)
                .mapError { it as Failure }
        }
    }

    fun completeSession(
        session: GameSession,
        durationSecondsRaw: Int,
        completionRaw: Int
    ): Result<GameSession, Failure> {
        val duration = PositiveInt.create(durationSecondsRaw).mapError { it as Failure }
        val completion = Percentage.create(completionRaw).mapError { it as Failure }

        return when {
            duration is Result.Err -> Result.err(duration.error)
            completion is Result.Err -> Result.err(completion.error)
            else -> GameSession.complete(session, (duration as Result.Ok).value, (completion as Result.Ok).value)
                .mapError { it as Failure }
        }
    }
}
