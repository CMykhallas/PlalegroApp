package com.pLg.app.ui.contents

import com.pLg.data.repo.impl.ContentRepositoryImpl
import com.pLg.data.local.source.LocalDataSource
import com.pLg.core.model.Content
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

class ContentRepositoryImplTest {

    @Test
    fun repository_refresh_and_observe_content() = runTest {
        val local = LocalDataSource() // supondo fake local
        val repo = ContentRepositoryImpl(local)

        repo.refreshContentPacks()
        val result = repo.getContentById("c1")

        assertTrue(result.isSuccess)
    }
}
