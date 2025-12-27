package com.plg.source.local.db.dao

import androidx.room.*
import com.plg.source.local.db.entity.ChildProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChildProfileDao {

    @Query("SELECT * FROM child_profiles ORDER BY createdAt DESC")
    fun getAllProfiles(): Flow<List<ChildProfileEntity>>

    @Query("SELECT * FROM child_profiles WHERE id = :id LIMIT 1")
    fun getProfileById(id: String): Flow<ChildProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ChildProfileEntity)

    @Update
    suspend fun updateProfile(profile: ChildProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ChildProfileEntity)

    @Query("DELETE FROM child_profiles")
    suspend fun clearAll()
}
