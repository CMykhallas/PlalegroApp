package com.pLg.core.i18n

/**
 * Chave forte para mensagens (evita strings vazias).
 */
@JvmInline
value class MessageKey(val value: String) {
    init {
        require(value.isNotBlank()) { "MessageKey não pode ser vazia" }
    }

    override fun toString(): String = value
}
