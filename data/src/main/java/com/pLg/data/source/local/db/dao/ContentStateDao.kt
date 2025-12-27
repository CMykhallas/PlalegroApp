package com.plg.source.local.db.dao

import androidx.room.*
import com.plg.source.local.db.entity.ContentStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentStateDao {

    @Query("SELECT * FROM content_states WHERE lessonId = :lessonId")
    fun getStatesForLesson(lessonId: String): Flow<List<ContentStateEntity>>

    @Query("SELECT * FROM content_states WHERE id = :id LIMIT 1")
    fun getStateById(id: String): Flow<ContentStateEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertState(state: ContentStateEntity)

    @Update
    suspend fun updateState(state: ContentStateEntity)

    @Delete
    suspend fun deleteState(state: ContentStateEntity)

    @Query("DELETE FROM content_states")
    suspend fun clearAll()
}
