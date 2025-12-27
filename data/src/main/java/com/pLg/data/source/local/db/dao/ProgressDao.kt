package com.plg.source.local.db.dao

import androidx.room.*
import com.plg.source.local.db.entity.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {

    @Query("SELECT * FROM progress WHERE childId = :childId ORDER BY updatedAt DESC")
    fun getProgressByChild(childId: String): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress WHERE lessonId = :lessonId AND childId = :childId LIMIT 1")
    fun getProgressForLesson(childId: String, lessonId: String): Flow<ProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity)

    @Update
    suspend fun updateProgress(progress: ProgressEntity)

    @Delete
    suspend fun deleteProgress(progress: ProgressEntity)

    @Query("DELETE FROM progress WHERE childId = :childId")
    suspend fun clearProgressForChild(childId: String)
}
