package com.pLg.core.common

interface Logger {
    fun logInfo(message: String)
    fun logError(message: String, throwable: Throwable? = null)
}

object ConsoleLogger : Logger {
    override fun logInfo(message: String) = println("INFO: $message")
    override fun logError(message: String, throwable: Throwable?) {
        println("ERROR: $message")
        throwable?.printStackTrace()
    }
}
