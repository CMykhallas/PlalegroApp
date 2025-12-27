package org.playlearn.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContentDao {
    @Query("SELECT * FROM contents")
    suspend fun getAll(): List<ContentEntity>

    @Query("SELECT * FROM contents WHERE id = :id")
    suspend fun getById(id: String): ContentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(contents: List<ContentEntity>)
}
