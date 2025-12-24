package com.pLg.core.user

import com.pLg.core.util.DataError
import com.pLg.core.util.Result

/**
 * Centralized validation rules for User.
 * Keeps consistency across creation, updates, and imports.
 */
object UserValidator {

    fun validateId(id: String): Result<String> =
        if (id.isNotBlank()) Result.Ok(id.trim())
        else Result.Err(DataError.Validation("User id must not be blank"))

    fun validateName(name: String): Result<String> =
        if (name.isNotBlank() && name.length <= 50) Result.Ok(name.trim())
        else Result.Err(DataError.Validation("User name invalid or too long"))

    fun validateEmail(email: String): Result<String> =
        if (email.contains("@") && email.contains(".")) Result.Ok(email.lowercase())
        else Result.Err(DataError.Validation("Invalid email format"))

    fun validate(user: User): Result<User> {
        val idRes = validateId(user.id)
        val nameRes = validateName(user.name)
        val emailRes = validateEmail(user.email)

        return if (idRes is Result.Ok && nameRes is Result.Ok && emailRes is Result.Ok) {
            Result.Ok(user.copy(id = idRes.value, name = nameRes.value, email = emailRes.value))
        } else {
            val err = listOf(idRes, nameRes, emailRes).filterIsInstance<Result.Err>().firstOrNull()
            err ?: Result.Err(DataError.Validation("Unknown validation error"))
        }
    }
}
