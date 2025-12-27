package org.playlearn.core.model

enum class ContentStatus { AVAILABLE, LOCKED }

data class Content(
    val id: String,
    val title: String,
    val level: Int,
    val status: ContentStatus,
    val metadata: Map<String, String>
)
