package com.pLg.core.usecase

import com.pLg.core.domain.*
import com.pLg.core.domain.value.*

/**
 * Caso de uso para registrar um novo usuário (responsável/educador).
 */
class RegisterUserUseCase {
    operator fun invoke(rawName: String, rawEmail: String): Result<User, Failure> {
        val name = NonEmptyString.create(rawName, minLen = 2, maxLen = 64).mapError { it as Failure }
        val email = Email.create(rawEmail).mapError { it as Failure }

        return when {
            name is Result.Err -> Result.err(name.error)
            email is Result.Err -> Result.err(email.error)
            else -> {
                val id = UserId.new()
                User.create(id, (name as Result.Ok).value, (email as Result.Ok).value)
                    .mapError { it as Failure }
            }
        }
    }
}
