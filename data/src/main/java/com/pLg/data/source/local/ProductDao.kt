package com.pLg.data.source.local

import com.pLg.data.model.entity.ProductEntity
import com.pLg.data.util.Page
import com.pLg.data.util.PageRequest
import com.pLg.data.util.Result

interface ProductDao {
    fun page(req: PageRequest): Result<Page<ProductEntity>>
    fun upsertAll(items: List<ProductEntity>): Result<Unit>
    fun clear(): Result<Unit>
}

class InMemoryProductDao : ProductDao {
    private val data = mutableListOf<ProductEntity>()

    override fun page(req: PageRequest): Result<Page<ProductEntity>> = safeLocal {
        val from = (req.page * req.size).coerceAtLeast(0)
        val to = (from + req.size).coerceAtMost(data.size)
        val slice = if (from >= data.size) emptyList() else data.subList(from, to)
        Page(items = slice.toList(), page = req.page, size = req.size, total = data.size.toLong())
    }

    override fun upsertAll(items: List<ProductEntity>): Result<Unit> = safeLocal {
        // substitui itens por id (simples para exemplo)
        val map = data.associateBy { it.id }.toMutableMap()
        items.forEach { map[it.id] = it }
        data.clear()
        data.addAll(map.values)
    }

    override fun clear(): Result<Unit> = safeLocal { data.clear() }
}
