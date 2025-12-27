package org.playlearn.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.playlearn.domain.repo.UserRepository

class LoginUserUseCaseTest {
    private val repo: UserRepository = mock()
    private val useCase = LoginUserUseCase(repo)

    @Test
    fun `login success returns user`() = runTest {
        val expectedUser = User("u1", "Alice", 7, "pt-MZ")
        whenever(repo.login("Alice", "1234")).thenReturn(expectedUser)

        val result = useCase("Alice", "1234")
        assertEquals(expectedUser, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `login fails throws exception`() = runTest {
        whenever(repo.login("Bob", "wrong")).thenThrow(IllegalArgumentException("Invalid credentials"))
        useCase("Bob", "wrong")
    }
}
