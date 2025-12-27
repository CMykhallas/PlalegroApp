package com.plg.source.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child_profiles")
data class ChildProfileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val age: Int,
    val createdAt: Long,
    val nickname: String? = null // adicionado na Migration 1→2
)
