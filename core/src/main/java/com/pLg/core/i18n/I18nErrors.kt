package com.pLg.core.i18n

/**
 * Erros explícitos do subsistema i18n.
 */
sealed class I18nError(message: String, cause: Throwable? = null) : RuntimeException(message, cause) {

    class MissingKey(val key: MessageKey, val chain: List<String>) :
        I18nError("Chave '${key.value}' não encontrada nos locales: $chain")

    class CatalogLoad(message: String, cause: Throwable? = null) :
        I18nError(message, cause)

    class InvalidTemplate(message: String) :
        I18nError(message)
}
