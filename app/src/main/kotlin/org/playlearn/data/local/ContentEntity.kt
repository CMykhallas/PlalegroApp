package org.playlearn.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents")
data class ContentEntity(
    @PrimaryKey val id: String,
    val title: String,
    val level: Int,
    val status: String,
    val metadataJson: String
)
