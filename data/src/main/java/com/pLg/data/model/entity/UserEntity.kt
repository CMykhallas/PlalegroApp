package com.pLg.data.model.entity

data class UserEntity(
    val id: String,
    val name: String,
    val email: String,
    val createdAtEpochMillis: Long
)
