package com.pLg.core.domain

/**
 * Domain failures kept explicit and type-safe.
 */
sealed interface Failure {
    val message: String
}

sealed class ValidationFailure(override val message: String) : Failure {
    object Empty : ValidationFailure("Value must not be empty")
    object TooShort : ValidationFailure("Value is too short")
    object TooLong : ValidationFailure("Value is too long")
    object InvalidFormat : ValidationFailure("Invalid format")
    object OutOfRange : ValidationFailure("Value out of allowed range")
    data class Custom(override val message: String) : ValidationFailure(message)
}

sealed class BusinessFailure(override val message: String) : Failure {
    data class NotFound(val entity: String, val id: String) : BusinessFailure("Not found: $entity with id=$id")
    data class Conflict(val reason: String) : BusinessFailure("Conflict: $reason")
    data class Forbidden(val reason: String) : BusinessFailure("Forbidden: $reason")
}
