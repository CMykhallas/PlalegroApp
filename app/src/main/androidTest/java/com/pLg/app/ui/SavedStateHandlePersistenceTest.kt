package com.pLg.app.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.lifecycle.SavedStateHandle
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.core.model.User
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class SavedStateHandlePersistenceTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject lateinit var viewModel: UserViewModel

    @Test
    fun user_id_persists_after_rotation() {
        hiltRule.inject()

        // Simula parâmetro de navegação salvo no SavedStateHandle
        viewModel.savedStateHandle["userId"] = "u1"
        viewModel.fakeFlow = MutableStateFlow(User("u1", "Alice", 7, "pt-MZ"))

        // Verifica se o nome aparece
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // Rotaciona a tela
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.setOrientationLandscape()

        // Verifica novamente após rotação
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // Volta para portrait
        device.setOrientationPortrait()
    }
}
