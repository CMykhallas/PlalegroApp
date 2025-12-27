package org.playlearn.core.util

object Logger {
    fun logEvent(event: String, details: Map<String, Any> = emptyMap()) {
        println("[EVENT] $event - $details")
        // Aqui pode integrar Firebase Analytics ou outro serviço
    }
}
