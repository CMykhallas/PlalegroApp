package com.pLg.core.i18n

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Formatadores com reconhecimento de locale (JVM).
 */
object Formatters {

    fun number(localeTag: String, value: Number): String {
        val locale = Locale.forLanguageTag(localeTag)
        return NumberFormat.getNumberInstance(locale).format(value)
    }

    fun currency(localeTag: String, value: Number, currencyCode: String): String {
        val locale = Locale.forLanguageTag(localeTag)
        val fmt = NumberFormat.getCurrencyInstance(locale).apply {
            currency = java.util.Currency.getInstance(currencyCode)
        }
        return fmt.format(value)
    }

    fun dateMedium(localeTag: String, date: LocalDate): String {
        val locale = Locale.forLanguageTag(localeTag)
        val fmt = DateTimeFormatter.ofPattern("d MMM y", locale)
        return date.format(fmt)
    }
}
