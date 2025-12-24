package com.pLg.shared.net

import com.pLg.shared.domain.Failure
import com.pLg.shared.domain.Result
import com.pLg.shared.domain.ValidationFailure
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerializationException

// Expect/Actual para configurar o client e base URL
public expect object HttpPlatform {
    public fun client(): io.ktor.client.HttpClient
    public fun baseUrl(): String
}

public suspend inline fun <reified T> getJson(path: String): Result<T, Failure> {
    val client = HttpPlatform.client()
    return try {
        val response = client.get("${HttpPlatform.baseUrl()}$path") {
            accept(ContentType.Application.Json)
        }
        if (response.status.isSuccess()) {
            Result.ok(response.body<T>())
        } else {
            Result.err(ValidationFailure.Custom("HTTP ${response.status.value}: ${response.status.description}"))
        }
    } catch (ex: SerializationException) {
        Result.err(ValidationFailure.InvalidFormat)
    } catch (ex: Exception) {
        Result.err(ValidationFailure.Custom(ex.message ?: "Unknown error"))
    }
}

public suspend inline fun <reified R, reified B> postJson(path: String, body: B): Result<R, Failure> {
    val client = HttpPlatform.client()
    return try {
        val response = client.post("${HttpPlatform.baseUrl()}$path") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        if (response.status.isSuccess()) {
            Result.ok(response.body<R>())
        } else {
            Result.err(ValidationFailure.Custom("HTTP ${response.status.value}: ${response.status.description}"))
        }
    } catch (ex: SerializationException) {
        Result.err(ValidationFailure.InvalidFormat)
    } catch (ex: Exception) {
        Result.err(ValidationFailure.Custom(ex.message ?: "Unknown error"))
    }
}
