package com.pLg.data.model.mapper

import com.pLg.data.model.dto.UserDto
import com.pLg.data.model.entity.UserEntity
import com.pLg.data.util.DataError
import com.pLg.data.util.Result
import java.time.Instant
import java.time.format.DateTimeParseException

/**
 * Mapper avançado para User.
 * - Valida campos obrigatórios
 * - Converte datas ISO para epochMillis
 * - Normaliza email e nome
 */
object UserMapper {

    fun dtoToEntity(dto: UserDto): Result<UserEntity> {
        if (dto.id.isBlank()) return Result.Err(DataError.Validation("User id vazio"))
        if (dto.email.isBlank()) return Result.Err(DataError.Validation("Email vazio"))

        return try {
            val epoch = Instant.parse(dto.createdAtIso).toEpochMilli()
            Result.Ok(
                UserEntity(
                    id = dto.id.trim(),
                    name = dto.name.trim(),
                    email = dto.email.lowercase(),
                    createdAtEpochMillis = epoch
                )
            )
        } catch (e: DateTimeParseException) {
            Result.Err(DataError.Serialization("Data inválida: ${dto.createdAtIso}", e))
        }
    }

    fun entityToDto(entity: UserEntity): UserDto =
        UserDto(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            createdAtIso = Instant.ofEpochMilli(entity.createdAtEpochMillis).toString()
        )

    // Extensão para converter para domain model
    fun UserEntity.toDomain(): com.pLg.core.user.User =
        com.pLg.core.user.User(
            id = id,
            name = name,
            email = email,
            createdAtEpochMillis = createdAtEpochMillis
        )
}
