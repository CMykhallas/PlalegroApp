package com.pLg.server

import com.pLg.shared.domain.Result
import com.pLg.shared.domain.UseCases
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) { json() }
        routing {
            post("/registerUser") {
                val req = call.receive<Map<String, String>>()
                val result = UseCases.registerUser(req["name"] ?: "", req["email"] ?: "")
                when (result) {
                    is Result.Ok -> call.respond(result.value)
                    is Result.Err -> call.respond(mapOf("error" to result.error.message))
                }
            }

            post("/createChild") {
                val req = call.receive<Map<String, String>>()
                val result = UseCases.createChildProfile(
                    req["displayName"] ?: "",
                    req["age"]?.toIntOrNull() ?: -1,
                    req["locale"] ?: ""
                )
                when (result) {
                    is Result.Ok -> call.respond(result.value)
                    is Result.Err -> call.respond(mapOf("error" to result.error.message))
                }
            }
        }
    }.start(wait = true)
}
