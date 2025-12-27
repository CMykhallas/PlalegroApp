package com.pLg.app.ui.contents

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pLg.data.local.db.AppDatabase
import com.pLg.data.local.db.ContentDao
import com.pLg.core.model.ContentEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

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
    fun insert_and_get_content() = runTest {
        val entity = ContentEntity("c1", "Matemática Básica", 1, "AVAILABLE")
        dao.insert(entity)

        val loaded = dao.getById("c1")
        assertEquals("Matemática Básica", loaded?.title)
    }
}
