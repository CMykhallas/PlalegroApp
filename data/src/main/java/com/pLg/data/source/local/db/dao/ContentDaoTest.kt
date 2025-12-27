package com.pLg.data.local.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pLg.data.local.db.AppDatabase
import com.pLg.data.local.db.entity.ContentEntity
import kotlinx.coroutines.runBlocking
import org.junit.*

@RunWith(AndroidJUnit4::class)
class ContentDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: ContentDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.contentDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insert_and_retrieve_content() = runBlocking {
        val entity = ContentEntity("id1", "Title", 1, "AVAILABLE", "{}", System.currentTimeMillis())
        dao.upsertAll(listOf(entity))

        val retrieved = dao.getById("id1")
        Assert.assertEquals("Title", retrieved?.title)
    }
}
