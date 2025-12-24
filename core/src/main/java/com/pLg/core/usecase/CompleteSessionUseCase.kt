package com.pLg.core.usecase

import com.pLg.core.domain.*
import com.pLg.core.domain.value.*

/**
 * Caso de uso para completar uma sessão de jogo.
 */
class CompleteSessionUseCase {
    operator fun invoke(session: GameSession, durationSecondsRaw: Int, completionRaw: Int): Result<GameSession, Failure> {
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
