package com.pLg.app.ui.componentes.screens

import com.pLg.data.repo.contract.ContentRepository
import com.pLg.core.model.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeContentRepo : ContentRepository {
    private val flow = MutableStateFlow<List<Content>>(emptyList())

    override suspend fun refreshContentPacks(): Result<Unit> = runCatching {
        flow.value = listOf(
            Content("c1", "Matemática Básica", 1, "AVAILABLE", emptyMap()),
            Content("c2", "Ciências Naturais", 1, "LOCKED", emptyMap())
        )
    }

    override fun observeContent(): Flow<List<Content>> = flow

    override suspend fun getContentById(id: String): Result<Content> =
        runCatching { flow.value.first { it.id == id } }
}
