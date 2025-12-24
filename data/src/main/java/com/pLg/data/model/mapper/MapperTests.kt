package com.pLg.data.model.mapper

import com.pLg.data.model.dto.UserDto
import com.pLg.data.model.dto.ProductDto
import com.pLg.data.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MapperTests {

    @Test
    fun userDtoToEntity_valid() {
        val dto = UserDto("u1", "Ana", "ANA@EXAMPLE.COM", "2025-12-24T09:50:00Z")
        val res = UserMapper.dtoToEntity(dto)
        assertTrue(res is Result.Ok)
        val entity = (res as Result.Ok).value
        assertEquals("ana@example.com", entity.email)
    }

    @Test
    fun userDtoToEntity_invalidDate() {
        val dto = UserDto("u1", "Ana", "ana@example.com", "invalid-date")
        val res = UserMapper.dtoToEntity(dto)
        assertTrue(res is Result.Err)
    }

    @Test
    fun productDtoToEntity_valid() {
        val dto = ProductDto("p1", "Laptop", 1234.56, "usd")
        val res = ProductMapper.dtoToEntity(dto)
        assertTrue(res is Result.Ok)
        val entity = (res as Result.Ok).value
        assertEquals(123456, entity.priceMinorUnits)
        assertEquals("USD", entity.currency)
    }

    @Test
    fun productDtoToEntity_invalidPrice() {
        val dto = ProductDto("p1", "Laptop", -1.0, "USD")
        val res = ProductMapper.dtoToEntity(dto)
        assertTrue(res is Result.Err)
    }
}
