package com.pLg.core.i18n

/**
 * Calcula a sequência ordenada de fallbacks de locale.
 * Ex.: pt-MZ → [pt-MZ, pt, en]
 */
class LocaleChain(
    private val preferred: LocaleSpec,
    private val defaults: List<LocaleSpec> = listOf(LocaleSpec("en"))
) {

    fun sequence(): List<String> {
        val l = preferred.language
        val s = preferred.script
        val r = preferred.region

        val seq = mutableListOf<String>()
        fun add(tag: String?) { if (!tag.isNullOrBlank()) seq.add(tag) }

        // ordem detalhada, evitando duplicações
        add(LocaleSpec.buildTag(l, s, r))
        add(LocaleSpec.buildTag(l, null, r))
        add(LocaleSpec.buildTag(l, s, null))
        add(LocaleSpec.buildTag(l, null, null))

        defaults.forEach { add(it.tag) }
        return seq.distinct()
    }
}
