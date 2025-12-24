package com.pLg.data.source.remote

interface ApiClient {
    suspend fun get(path: String, query: Map<String, String> = emptyMap()): Response
    suspend fun post(path: String, body: String): Response
    suspend fun put(path: String, body: String): Response
    suspend fun delete(path: String): Response
}

data class Response(
    val code: Int,
    val body: String
) {
    val isSuccessful: Boolean get() = code in 200..299
}
