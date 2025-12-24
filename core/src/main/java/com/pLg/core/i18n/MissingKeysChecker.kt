package com.pLg.core.i18n

object MissingKeysChecker {

    /**
     * Retorna mapa de locale -> chaves faltantes.
     */
    fun check(catalog: MessageCatalog): Map<String, Set<String>> {
        val allLocales = catalog.locales()
        val allKeys = allLocales.flatMap { locale ->
            catalogKeys(catalog, locale)
        }.toSet()

        val missing = mutableMapOf<String, Set<String>>()
        for (locale in allLocales) {
            val keys = catalogKeys(catalog, locale)
            val diff = allKeys - keys
            if (diff.isNotEmpty()) {
                missing[locale] = diff
            }
        }
        return missing
    }

    private fun catalogKeys(catalog: MessageCatalog, locale: String): Set<String> {
        // hack: não temos API para listar todas as chaves, então assumimos InMemoryMessageCatalog
        return if (catalog is InMemoryMessageCatalog) {
            catalog.locales().flatMap { l ->
                catalogKeysInternal(catalog, l)
            }.toSet()
        } else emptySet()
    }

    private fun catalogKeysInternal(catalog: InMemoryMessageCatalog, locale: String): Set<String> {
        val field = InMemoryMessageCatalog::class.java.getDeclaredField("data")
        field.isAccessible = true
        val data = field.get(catalog) as Map<*, *>
        val messages = data[locale] as? Map<*, *> ?: emptyMap<Any, Any>()
        return messages.keys.filterIsInstance<String>().toSet()
    }
}
