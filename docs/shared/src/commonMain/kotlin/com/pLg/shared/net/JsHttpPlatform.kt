package com.pLg.shared.net

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
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
        HttpClient(Js) {
            install(ContentNegotiation) { json(json) }
        }
    }

    public actual fun client(): HttpClient = clientInstance

    public actual fun baseUrl(): String = js("process?.env?.API_BASE_URL") as? String ?: "https://api.example.com"
}
