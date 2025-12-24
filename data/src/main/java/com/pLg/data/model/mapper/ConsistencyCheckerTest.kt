package com.pLg.data.model.mapper

import com.pLg.data.model.dto.UserDto
import com.pLg.data.model.dto.ProductDto
import com.pLg.data.model.entity.UserEntity
import com.pLg.data.model.entity.ProductEntity
import com.pLg.data.util.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant

class ConsistencyCheckerTest {

    @Test
    fun userDtoEntityRoundTrip() {
        val dto = UserDto("u1", "Ana", "ana@example.com", "2025-12-24T09:50:00Z")
        val entityRes = UserMapper.dtoToEntity(dto)
        assertTrue(entityRes is Result.Ok)
        val entity = (entityRes as Result.Ok).value
        val dto2 = UserMapper.entityToDto(entity)
        assertEquals(dto.id, dto2.id)
        assertEquals(dto.email.lowercase(), dto2.email)
    }

    @Test
    fun productDtoEntityRoundTrip() {
        val dto = ProductDto("p1", "Laptop", 1234.56, "USD")
        val entityRes = ProductMapper.dtoToEntity(dto)
        assertTrue(entityRes is Result.Ok)
        val entity = (entityRes as Result.Ok).value
        val dto2 = ProductMapper.entityToDto(entity)
        assertEquals(dto.id, dto2.id)
        assertEquals(dto.currency.uppercase(), dto2.currency)
        assertEquals(dto.price, dto2.price, 0.01)
    }

    @Test
    fun userEntityToDomainConsistency() {
        val entity = UserEntity("u1", "Ana", "ana@example.com", Instant.now().toEpochMilli())
        val domain = UserMapper.run { entity.toDomain() }
        assertEquals(entity.id, domain.id)
        assertEquals(entity.email, domain.email)
    }

    @Test
    fun productEntityToDomainConsistency() {
        val entity = ProductEntity("p1", "Laptop", 123456, "USD")
        val domain = ProductMapper.run { entity.toDomain() }
        assertEquals(entity.id, domain.id)
        assertEquals(entity.currency, domain.currency)
    }
}
