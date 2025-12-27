package org.playlearn.domain.usecase

import org.playlearn.core.model.User
import org.playlearn.core.util.AppResult
import org.playlearn.domain.repo.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(name: String, age: Int, locale: String = "pt-MZ"): AppResult<User> {
        val user = User(id = "u_${System.currentTimeMillis()}", name = name, age = age, locale = locale)
        return userRepository.register(user)
    }
}
