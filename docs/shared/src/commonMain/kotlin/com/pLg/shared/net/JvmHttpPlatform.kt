package com.pLg.shared.net

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

public actual object HttpPlatform {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    private val clientInstance: HttpClient by lazy {
        HttpClient(Java) {
            install(ContentNegotiation) { json(json) }
        }
    }

    public actual fun client(): HttpClient = clientInstance

    public actual fun baseUrl(): String = System.getProperty("API_BASE_URL") ?: "https://api.example.com"
}
