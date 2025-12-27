package org.playlearn.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preferences")
data class PreferencesEntity(
    @PrimaryKey val key: String = "default",
    val locale: String,
    val darkTheme: Boolean,
    val notificationsEnabled: Boolean
)
