package com.pLg.data.repo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pLg.core.model.User
import com.pLg.data.local.db.AppDatabase
import com.pLg.data.local.prefs.PreferencesDataSource
import com.pLg.data.local.source.LocalDataSource
import com.pLg.data.mapper.UserMapper
import com.pLg.data.remote.source.RemoteDataSource
import com.pLg.data.repo.impl.UserRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.*

class UserRepositoryImplTest {

    private lateinit var db: AppDatabase
    private lateinit var local: LocalDataSource
    private lateinit var prefs: PreferencesDataSource
    private lateinit var remote: RemoteDataSource
    private lateinit var mapper: UserMapper
    private lateinit var repo: UserRepositoryImpl

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()

        local = LocalDataSource(db)
        prefs = PreferencesDataSource(context)
        remote = RemoteDataSource(
            contentApi = { listOf() },
            userApi = { dto -> dto } // fake API returns same user
        )
        mapper = UserMapper()
        repo = UserRepositoryImpl(local, prefs, remote, mapper)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun register_and_retrieve_user() = runBlocking {
        val user = User("u1", "Alice", 7, "pt-MZ")
        val result = repo.register(user)

        Assert.assertTrue(result.isSuccess)
        val retrieved = repo.currentUser().getOrNull()
        Assert.assertEquals("Alice", retrieved?.name)
    }
}
