package com.pLg.data.model.mapper

import com.pLg.data.model.dto.ProductDto
import com.pLg.data.model.entity.ProductEntity
import com.pLg.data.util.DataError
import com.pLg.data.util.Result

/**
 * Mapper avançado para Product.
 * - Valida preço e moeda
 * - Converte preço para minor units (centavos)
 * - Normaliza título
 */
object ProductMapper {

    fun dtoToEntity(dto: ProductDto): Result<ProductEntity> {
        if (dto.id.isBlank()) return Result.Err(DataError.Validation("Product id vazio"))
        if (dto.title.isBlank()) return Result.Err(DataError.Validation("Título vazio"))
        if (dto.currency.isBlank()) return Result.Err(DataError.Validation("Moeda vazia"))
        if (dto.price.isNaN() || dto.price < 0.0) {
            return Result.Err(DataError.Validation("Preço inválido: ${dto.price}"))
        }

        val minorUnits = (dto.price * 100).toLong()
        return Result.Ok(
            ProductEntity(
                id = dto.id.trim(),
                title = dto.title.trim(),
                priceMinorUnits = minorUnits,
                currency = dto.currency.uppercase()
            )
        )
    }

    fun entityToDto(entity: ProductEntity): ProductDto =
        ProductDto(
            id = entity.id,
            title = entity.title,
            price = entity.priceMinorUnits / 100.0,
            currency = entity.currency
        )

    // Extensão para converter para domain model
    fun ProductEntity.toDomain(): com.pLg.core.product.Product =
        com.pLg.core.product.Product(
            id = id,
            title = title,
            priceMinorUnits = priceMinorUnits,
            currency = currency
        )
}
