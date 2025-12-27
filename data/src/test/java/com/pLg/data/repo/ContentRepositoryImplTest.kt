package com.pLg.data.repo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pLg.core.model.Content
import com.pLg.data.local.db.AppDatabase
import com.pLg.data.local.source.LocalDataSource
import com.pLg.data.mapper.ContentMapper
import com.pLg.data.remote.source.RemoteDataSource
import com.pLg.data.repo.impl.ContentRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class ContentRepositoryImplTest {

    private lateinit var db: AppDatabase
    private lateinit var local: LocalDataSource
    private lateinit var remote: RemoteDataSource
    private lateinit var mapper: ContentMapper
    private lateinit var repo: ContentRepositoryImpl

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        local = LocalDataSource(db)
        remote = RemoteDataSource(
            contentApi = { listOf() }, // fake API
            userApi = { TODO() }
        )
        mapper = ContentMapper()
        repo = ContentRepositoryImpl(local, remote, mapper)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insert_and_observe_content() = runBlocking {
        val content = Content("id1", "Title", 1, "AVAILABLE", emptyMap())
        val entity = mapper.toEntity(
            com.pLg.data.remote.model.ContentDto(
                id = content.id,
                title = content.title,
                version = content.packVersion,
                state = content.state,
                metadata = content.metadata
            )
        )

        local.saveContentPacks(listOf(entity))

        val observed = repo.observeContent().first()
        Assert.assertEquals("Title", observed.first().title)
    }
}
