package com.pLg.core.i18n

/**
 * Abstração de armazenamento de mensagens por locale.
 */
interface MessageCatalog {
    fun get(localeTag: String, key: MessageKey): String?
    fun locales(): Set<String>
}
