package com.pLg.app.ui

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.core.model.User
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class UserScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun user_name_is_displayed() {
        val fakeUser = User("u1", "Alice", 7, "pt-MZ")

        composeTestRule.setContent {
            UserScreen(userFlow = flowOf(fakeUser))
        }

        composeTestRule.onNodeWithText("Alice").assertIsDisplayed()
    }
}

@Composable
fun UserScreen(userFlow: kotlinx.coroutines.flow.Flow<User>) {
    val user by userFlow.collectAsState(initial = null)
    user?.let {
        BasicText(it.name)
    }
}
