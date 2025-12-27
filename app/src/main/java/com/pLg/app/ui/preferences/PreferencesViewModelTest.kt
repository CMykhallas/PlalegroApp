package com.pLg.app.ui.preferences

import com.pLg.app.ui.viewmodel.PreferencesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PreferencesViewModelTest {

    @Test
    fun preferences_viewmodel_updates_locale() = runTest {
        val repo = FakeUserRepo()
        val vm = PreferencesViewModel(repo)

        vm.updateLocale("en-US")
        val locale = vm.locale.first()

        assertEquals("en-US", locale)
    }
}
