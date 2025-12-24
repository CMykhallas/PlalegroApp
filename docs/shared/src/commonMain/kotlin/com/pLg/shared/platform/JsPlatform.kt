package com.pLg.shared.domain

import kotlin.js.Date

// Simple UUID v4-like generator for JS (not cryptographically strong)
private fun uuidJs(): String {
    fun s4(): String = (js("Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1)") as String)
    return "${s4()}${s4()}-${s4()}-${s4()}-${s4()}-${s4()}${s4()}${s4()}"
}

public actual object IdGenerator {
    public actual fun uuid(): String = uuidJs()
}

public actual object Clock {
    public actual fun nowIso(): String = Date().toISOString()
}
