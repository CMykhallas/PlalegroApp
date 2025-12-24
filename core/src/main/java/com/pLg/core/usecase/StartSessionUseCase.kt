package com.pLg.core.usecase

import com.pLg.core.domain.*
import com.pLg.core.domain.value.*

/**
 * Caso de uso para iniciar uma sessão de jogo.
 */
class StartSessionUseCase {
    operator fun invoke(childIdRaw: String, packIdRaw: String): Result<GameSession, Failure> {
        val childId = ChildId.create(childIdRaw).mapError { it as Failure }
        val packId = ContentPackId.create(packIdRaw).mapError { it as Failure }

        return when {
            childId is Result.Err -> Result.err(childId.error)
            packId is Result.Err -> Result.err(packId.error)
            else -> GameSession.start((childId as Result.Ok).value, (packId as Result.Ok).value)
                .mapError { it as Failure }
        }
    }
}
