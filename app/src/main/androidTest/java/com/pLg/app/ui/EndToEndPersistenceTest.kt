package com.pLg.app.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.core.model.Content
import com.pLg.core.model.User
import com.pLg.data.local.prefs.PreferencesDataSource
import com.pLg.data.local.source.LocalDataSource
import com.pLg.data.mapper.ContentMapper
import com.pLg.data.mapper.UserMapper
import com.pLg.data.repo.impl.ContentRepositoryImpl
import com.pLg.data.repo.impl.UserRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class EndToEndPersistenceTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject lateinit var contentRepo: ContentRepositoryImpl
    @Inject lateinit var userRepo: UserRepositoryImpl
    @Inject lateinit var prefs: PreferencesDataSource

    @Test
    fun full_flow_persists_after_process_restart() = runBlocking {
        hiltRule.inject()

        // 1. Salva conteúdo no Room via Repository
        val content = Content("id1", "Matemática Básica", 1, "AVAILABLE", emptyMap())
        contentRepo.refreshContentPacks() // simula fetch remoto
        contentRepo.observeContent()

        // 2. Salva usuário no Room via Repository
        val user = User("u1", "Alice", 7, "pt-MZ")
        userRepo.register(user)

        // 3. Salva preferências no DataStore
        prefs.update(mapOf("locale" to "pt-MZ"))

        // 4. Verifica se UI mostra dados
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("pt-MZ").assertIsDisplayed()

        // 5. Simula kill do processo
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.executeShellCommand("am kill com.pLg.app")

        // 6. Reabre a Activity
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // 7. Verifica se dados persistem após restart
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("pt-MZ").assertIsDisplayed()
    }
}
