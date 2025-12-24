package com.pLg.core.i18n

import java.time.LocalDate

/**
 * Fachada de i18n: uso simples em domínio e casos de uso.
 */
class I18n(
    val catalog: MessageCatalog,
    val localeProvider: LocaleProvider,
    val resolver: MessageResolver = MessageResolver(catalog, localeProvider)
) {
    fun t(key: String, args: Map<String, Any?> = emptyMap()): String =
        resolver.resolve(MessageKey(key), args)

    fun number(value: Number): String = Formatters.number(localeProvider.current().tag, value)

    fun currency(value: Number, currencyCode: String): String =
        Formatters.currency(localeProvider.current().tag, value, currencyCode)

    fun dateMedium(date: LocalDate): String =
        Formatters.dateMedium(localeProvider.current().tag, date)
}
