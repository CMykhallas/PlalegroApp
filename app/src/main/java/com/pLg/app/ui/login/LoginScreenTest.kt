package com.pLg.app.ui.login

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.login.LoginScreen
import com.pLg.app.ui.viewmodel.LoginViewModel
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun login_screen_accepts_input_and_triggers_success() {
        val viewModel = LoginViewModel(FakeUserRepo(), androidx.lifecycle.SavedStateHandle())
        var successCalled = false

        composeRule.setContent {
            LoginScreen(viewModel = viewModel, onSuccess = { successCalled = true })
        }

        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        viewModel.login("Alice", 7)
        assert(successCalled)
    }
}
