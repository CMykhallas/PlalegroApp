package com.plg.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content_states")
data class ContentStateEntity(
    @PrimaryKey val id: String,
    val lessonId: String,
    val state: String // pode ser enum convertido via TypeConverter
)
