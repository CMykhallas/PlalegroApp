package com.pLg.core.domain

import com.pLg.core.domain.value.Email
import com.pLg.core.domain.value.NonEmptyString
import com.pLg.core.domain.value.UserId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `should create valid user`() {
        val id = UserId.new()
        val name = NonEmptyString.create("Alice").getOrNull()
        val email = Email.create("alice@example.com").getOrNull()

        assertNotNull(name)
        assertNotNull(email)

        val user = User.create(id, name!!, email!!)
        assertTrue(user is Result.Ok)
    }

    @Test
    fun `should fail with invalid email`() {
        val id = UserId.new()
        val name = NonEmptyString.create("Bob").getOrNull()
        val email = Email.create("invalid-email")

        assertTrue(email is Result.Err)
    }
}
