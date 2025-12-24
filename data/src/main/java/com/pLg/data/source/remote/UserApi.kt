package com.pLg.data.source.remote

import com.pLg.data.model.dto.UserDto
import com.pLg.data.util.Result
import com.pLg.data.util.DataError
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class UserDtoSer(val id: String, val name: String, val email: String, val createdAtIso: String)

object UserApi {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getUser(api: ApiClient, id: String): Result<UserDto> {
        val res = api.get("/users/$id")
        val ok = res.requireSuccess()
        return when (ok) {
            is Result.Ok -> {
                try {
                    val s = json.decodeFromString(UserDtoSer.serializer(), ok.value)
                    Result.Ok(UserDto(s.id, s.name, s.email, s.createdAtIso))
                } catch (e: Exception) {
                    Result.Err(DataError.Serialization("User decode error", e))
                }
            }
            is Result.Err -> ok
        }
    }
}
