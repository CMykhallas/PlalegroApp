package com.plg.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val lessonId: String,
    val percentage: Int,
    val updatedAt: Long
)
