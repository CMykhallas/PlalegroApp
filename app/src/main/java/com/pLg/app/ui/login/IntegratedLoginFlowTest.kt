package com.pLg.app.ui.login

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.login.LoginScreen
import com.pLg.app.ui.viewmodel.LoginViewModel
import org.junit.Rule
import org.junit.Test

class IntegratedLoginFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_login_flow_registers_and_navigates() {
        val repo = FakeUserRepo()
        val vm = LoginViewModel(repo, androidx.lifecycle.SavedStateHandle())
        var navigated = false

        composeRule.setContent {
            LoginScreen(viewModel = vm, onSuccess = { navigated = true })
        }

        // Preenche campos
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")

        // Clica login
        composeRule.onNodeWithTag("login_button").performClick()
        vm.login("Alice", 7)

        // Verifica navegação
        assert(navigated)
    }
}
