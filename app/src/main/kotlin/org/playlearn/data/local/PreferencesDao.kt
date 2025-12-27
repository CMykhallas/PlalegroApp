package org.playlearn.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PreferencesDao {
    @Query("SELECT * FROM preferences WHERE key = 'default'")
    suspend fun get(): PreferencesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: PreferencesEntity)
}
