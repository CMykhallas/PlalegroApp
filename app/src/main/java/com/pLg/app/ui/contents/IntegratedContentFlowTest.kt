package com.pLg.app.ui.contents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.content.ContentScreen
import com.pLg.app.ui.viewmodel.ContentViewModel
import com.pLg.core.model.Content
import com.pLg.core.model.ContentEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class IntegratedContentFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_flow_content_repo_viewmodel_ui() = runTest {
        // 1. Setup fakes
        val fakeRepo = FakeContentRepo()
        val fakeLocal = FakeLocalDataSource()

        // 2. ViewModel com repo fake
        val vm = ContentViewModel(fakeRepo)

        // 3. Simula refresh no repo
        fakeRepo.refreshContentPacks()

        // 4. Insere no fake local
        val entity = ContentEntity("c1", "Matemática Básica", 1, "AVAILABLE")
        fakeLocal.insert(entity)

        // 5. Verifica se local devolve corretamente
        val loaded = fakeLocal.getById("c1")
        assertEquals("Matemática Básica", loaded?.title)

        // 6. Renderiza UI com Compose
        composeRule.setContent {
            ContentScreen(viewModel = vm, onProfile = {}, onPrefs = {})
        }

        // 7. Verifica se item aparece na UI
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
