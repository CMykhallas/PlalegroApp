package com.pLg.data.model.entity

data class ProductEntity(
    val id: String,
    val title: String,
    val priceMinorUnits: Long, // preço em centavos para precisão
    val currency: String
)
