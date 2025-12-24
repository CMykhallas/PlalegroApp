package com.pLg.core.domain

import com.pLg.core.domain.value.ChildId
import com.pLg.core.domain.value.ContentPackId
import com.pLg.core.domain.value.Percentage
import com.pLg.core.domain.value.PositiveInt

/**
 * Captures a single gameplay session aggregate for analytics and progress tracking.
 */
data class GameSession private constructor(
    val childId: ChildId,
    val contentPackId: ContentPackId,
    val durationSeconds: PositiveInt,
    val completion: Percentage
) {
    companion object {
        fun start(childId: ChildId, contentPackId: ContentPackId): Result<GameSession, ValidationFailure> {
            return Result.ok(
                GameSession(
                    childId = childId,
                    contentPackId = contentPackId,
                    durationSeconds = PositiveInt.create(1).getOrNull()
                        ?: return Result.err(ValidationFailure.OutOfRange),
                    completion = Percentage.create(0).getOrNull()
                        ?: return Result.err(ValidationFailure.OutOfRange)
                )
            )
        }

        fun complete(
            session: GameSession,
            durationSeconds: PositiveInt,
            completion: Percentage
        ): Result<GameSession, ValidationFailure> {
            if (completion.value < 100) return Result.err(ValidationFailure.OutOfRange)
            return Result.ok(session.copy(durationSeconds = durationSeconds, completion = completion))
        }
    }
}
