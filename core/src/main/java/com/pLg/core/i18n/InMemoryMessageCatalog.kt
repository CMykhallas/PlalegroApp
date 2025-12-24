package com.pLg.core.i18n

import java.util.concurrent.ConcurrentHashMap

/**
 * Catálogo thread-safe em memória, com APIs de mutação controladas.
 */
class InMemoryMessageCatalog(
    initialData: Map<String, Map<String, String>> = emptyMap()
) : MessageCatalog {

    private val data: ConcurrentHashMap<String, ConcurrentHashMap<String, String>> =
        ConcurrentHashMap()

    init {
        initialData.forEach { (locale, messages) ->
            data[locale] = ConcurrentHashMap(messages)
        }
    }

    override fun get(localeTag: String, key: MessageKey): String? =
        data[localeTag]?.get(key.value)

    override fun locales(): Set<String> = data.keys

    fun put(localeTag: String, key: MessageKey, value: String) {
        require(value.isNotBlank()) { "Valor de mensagem não pode ser vazio" }
        data.computeIfAbsent(localeTag) { ConcurrentHashMap() }[key.value] = value
    }

    fun replaceLocale(localeTag: String, messages: Map<String, String>) {
        val validated = messages.onEach { (k, v) ->
            require(k.isNotBlank()) { "Chave vazia não permitida" }
            require(v.isNotBlank()) { "Mensagem vazia não permitida" }
        }
        data[localeTag] = ConcurrentHashMap(validated)
    }
}
