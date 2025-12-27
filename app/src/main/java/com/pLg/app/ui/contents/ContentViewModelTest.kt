package com.pLg.app.ui.contents

import com.pLg.app.ui.viewmodel.ContentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ContentViewModelTest {

    @Test
    fun content_viewmodel_observes_content() = runTest {
        val repo = FakeContentRepo()
        val vm = ContentViewModel(repo)

        repo.refreshContentPacks()
        val contents = vm.contents.first()

        assertEquals("Matemática Básica", contents.first().title)
    }
}
