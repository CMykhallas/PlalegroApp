package com.pLg.data.source.remote

import com.pLg.data.model.dto.ProductDto
import com.pLg.data.util.Page
import com.pLg.data.util.PageRequest
import com.pLg.data.util.Result
import com.pLg.data.util.DataError
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ProductDtoSer(val id: String, val title: String, val price: Double, val currency: String)

@Serializable
data class ProductPageSer(val items: List<ProductDtoSer>, val total: Long)

object ProductApi {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getProducts(api: ApiClient, req: PageRequest): Result<Page<ProductDto>> {
        val res = api.get("/products", mapOf("page" to req.page.toString(), "size" to req.size.toString()))
        val ok = res.requireSuccess()
        return when (ok) {
            is Result.Ok -> {
                try {
                    val p = json.decodeFromString(ProductPageSer.serializer(), ok.value)
                    Result.Ok(
                        Page(
                            items = p.items.map { ProductDto(it.id, it.title, it.price, it.currency) },
                            page = req.page,
                            size = req.size,
                            total = p.total
                        )
                    )
                } catch (e: Exception) {
                    Result.Err(DataError.Serialization("Product page decode error", e))
                }
            }
            is Result.Err -> ok
        }
    }
}
