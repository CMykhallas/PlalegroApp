package com.pLg.data.util

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

data class RetryPolicy(
    val maxAttempts: Int = 3,
    val initialDelay: Duration = 200.milliseconds,
    val backoffFactor: Double = 2.0
)

suspend fun <T> retry(policy: RetryPolicy, block: suspend () -> T): T {
    var attempt = 0
    var delay = policy.initialDelay
    var last: Throwable? = null
    while (attempt < policy.maxAttempts) {
        try {
            return block()
        } catch (t: Throwable) {
            last = t
            attempt++
            if (attempt >= policy.maxAttempts) break
            kotlinx.coroutines.delay(delay)
            delay = (delay.inWholeMilliseconds * policy.backoffFactor).milliseconds
        }
    }
    throw last ?: IllegalStateException("Retry failed without throwable")
}
