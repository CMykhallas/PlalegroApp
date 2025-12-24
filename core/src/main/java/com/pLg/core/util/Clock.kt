package com.pLg.core.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Interface para abstrair acesso ao tempo.
 * Facilita testes e evita dependência direta de APIs de sistema.
 */
interface Clock {
    fun nowInstant(): Instant
    fun nowLocal(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime
}

/**
 * Implementação padrão usando o sistema.
 */
object SystemClock : Clock {
    override fun nowInstant(): Instant = Instant.now()
    override fun nowLocal(zoneId: ZoneId): LocalDateTime = LocalDateTime.now(zoneId)
}

/**
 * Implementação fixa para testes (tempo controlado).
 */
class FixedClock(private val instant: Instant) : Clock {
    override fun nowInstant(): Instant = instant
    override fun nowLocal(zoneId: ZoneId): LocalDateTime = LocalDateTime.ofInstant(instant, zoneId)
}
