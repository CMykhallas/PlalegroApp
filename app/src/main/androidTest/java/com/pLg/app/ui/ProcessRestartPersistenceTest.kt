package com.pLg.app.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.core.model.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ProcessRestartPersistenceTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject lateinit var viewModel: UserViewModel

    @Test
    fun user_persists_after_process_kill_and_restart() {
        hiltRule.inject()

        // Simula parâmetro salvo
        viewModel.savedStateHandle["userId"] = "u1"
        viewModel.fakeFlow = flowOf(User("u1", "Alice", 7, "pt-MZ"))

        // Verifica se aparece
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // Simula kill do processo
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.executeShellCommand("am kill com.pLg.app")

        // Reabre a Activity
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // Verifica se o estado foi restaurado
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
    }
}
