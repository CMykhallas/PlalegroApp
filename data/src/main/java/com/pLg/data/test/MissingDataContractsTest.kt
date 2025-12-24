package com.pLg.data.test

import com.pLg.data.model.dto.ProductDto
import com.pLg.data.model.mapper.ProductMapper
import com.pLg.data.util.Result
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MissingDataContractsTest {

    @Test
    fun invalidPriceFails() {
        val dto = ProductDto("p1", "Title", -1.0, "USD")
        val res = ProductMapper.dtoToEntity(dto)
        assertTrue(res is Result.Err)
    }
}
