package com.pLg.data.repo

import com.pLg.data.model.mapper.ProductMapper
import com.pLg.data.source.CachePolicy
import com.pLg.data.source.local.ProductDao
import com.pLg.data.source.remote.ApiClient
import com.pLg.data.source.remote.ProductApi
import com.pLg.data.util.Page
import com.pLg.data.util.PageRequest
import com.pLg.data.util.Result
import com.pLg.data.util.retry
import com.pLg.data.util.RetryPolicy

class ProductRepository(
    private val api: ApiClient,
    private val dao: ProductDao,
    private val retryPolicy: RetryPolicy = RetryPolicy()
) {

    suspend fun page(
        req: PageRequest,
        policy: CachePolicy = CachePolicy.NetworkFirst
    ): Result<Page<com.pLg.core.product.Product>> {
        return when (policy) {
            CachePolicy.CacheOnly -> {
                val local = dao.page(req)
                local.map { page -> page.map { it.toDomain() } }
            }
            CachePolicy.NetworkOnly -> fromNetwork(req, persist = true)
            CachePolicy.CacheFirst -> {
                val local = dao.page(req)
                if (local is Result.Ok && local.value.items.isNotEmpty()) {
                    local.map { page -> page.map { it.toDomain() } }
                } else {
                    fromNetwork(req, persist = true)
                }
            }
            CachePolicy.NetworkFirst -> {
                val net = fromNetwork(req, persist = true)
                if (net is Result.Ok) net else {
                    val local = dao.page(req)
                    local.map { page -> page.map { it.toDomain() } }
                }
            }
        }
    }

    suspend fun invalidate() {
        dao.clear()
    }

    private suspend fun fromNetwork(req: PageRequest, persist: Boolean): Result<Page<com.pLg.core.product.Product>> {
        val res = try {
            retry(retryPolicy) { ProductApi.getProducts(api, req) }
        } catch (t: Throwable) {
            return Result.Err(com.pLg.data.util.DataError.Network("Network retry exhausted", t))
        }
        return when (res) {
            is Result.Ok -> {
                val mapped = res.value.items.map { ProductMapper.dtoToEntity(it) }
                val firstErr = mapped.firstOrNull { it is Result.Err } as? Result.Err
                if (firstErr != null) return firstErr
                val entities = mapped.map { (it as Result.Ok).value }
                if (persist) dao.upsertAll(entities)
                Result.Ok(
                    Page(
                        items = entities.map { it.toDomain() },
                        page = res.value.page,
                        size = res.value.size,
                        total = res.value.total
                    )
                )
            }
            is Result.Err -> res
        }
    }
}

// Helpers
private fun com.pLg.data.model.entity.ProductEntity.toDomain(): com.pLg.core.product.Product =
    com.pLg.core.product.Product(
        id = id,
        title = title,
        priceMinorUnits = priceMinorUnits,
        currency = currency
    )

// Extension to map Page<T>
private fun <T, R> com.pLg.data.util.Page<T>.map(transform: (T) -> R): com.pLg.data.util.Page<R> =
    com.pLg.data.util.Page(
        items = items.map(transform),
        page = page,
        size = size,
        total = total
    )
