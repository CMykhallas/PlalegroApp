package com.pLg.app.ui.contents

import com.pLg.core.model.ContentEntity

class FakeLocalDataSource {
    private val storage = mutableMapOf<String, ContentEntity>()

    fun insert(entity: ContentEntity) {
        storage[entity.id] = entity
    }

    fun getById(id: String): ContentEntity? = storage[id]

    fun getAll(): List<ContentEntity> = storage.values.toList()
}
