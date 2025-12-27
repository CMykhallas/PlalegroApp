package com.pLg.app.ui.e2e

import com.pLg.data.repo.contract.ContentRepository
import com.pLg.data.repo.contract.UserRepository
import com.pLg.core.model.Content
import com.pLg.core.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

class FakeContentRepository : ContentRepository {
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

class FakeUserRepository : UserRepository {
    private var user: User? = null
    private var prefs: MutableMap<String, Any> = mutableMapOf()
    override suspend fun register(user: User): Result<User> = runCatching {
        this.user = user; user
    }
    override suspend fun currentUser(): Result<User?> = runCatching { user }
    override suspend fun updatePreferences(prefsMap: Map<String, Any>): Result<Unit> =
        runCatching { prefs.putAll(prefsMap) }
}

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [com.pLg.app.di.DataModule::class])
object HiltTestAppModule {
    @Provides @Singleton fun provideContentRepo(): ContentRepository = FakeContentRepository()
    @Provides @Singleton fun provideUserRepo(): UserRepository = FakeUserRepository()
}
