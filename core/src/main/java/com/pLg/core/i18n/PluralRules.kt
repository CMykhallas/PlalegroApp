package com.pLg.core.i18n

/**
 * Regras básicas de pluralização por idioma.
 * Pode ser expandida conforme necessidade (ICU-like).
 */
object PluralRules {
    enum class Category { ZERO, ONE, TWO, FEW, MANY, OTHER }

    fun categoryFor(language: String, n: Int): Category = when (language.lowercase()) {
        "pt" -> if (n == 1) Category.ONE else Category.OTHER
        "en" -> if (n == 1) Category.ONE else Category.OTHER
        else -> if (n == 1) Category.ONE else Category.OTHER
    }
}
