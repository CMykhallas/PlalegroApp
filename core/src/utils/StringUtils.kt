package com.pLg.core.utils

object StringUtils {
    fun isBlank(value: String?): Boolean = value == null || value.trim().isEmpty()

    fun normalizeWhitespace(value: String): String = value.trim().replace("\\s+".toRegex(), " ")

    fun capitalize(value: String): String = value.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    fun kebabCase(value: String): String = value.trim().lowercase().replace("\\s+".toRegex(), "-")

    fun snakeCase(value: String): String = value.trim().lowercase().replace("\\s+".toRegex(), "_")
}
