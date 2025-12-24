package com.pLg.core.i18n

data class LocaleSpec(
    val language: String,
    val region: String? = null,
    val script: String? = null,
) {
    val tag: String = buildTag(language, script, region)

    companion object {
        fun parse(raw: String): LocaleSpec {
            // Suporta tags tipo "pt", "pt-MZ", "zh-Hant-TW"
            val parts = raw.split('-', '_').filter { it.isNotBlank() }
            require(parts.isNotEmpty()) { "Locale vazio" }
            val lang = parts[0].lowercase()
            var script: String? = null
            var region: String? = null

            parts.drop(1).forEach { p ->
                when {
                    p.length == 4 -> script = p.replaceFirstChar { it.uppercase() }
                    p.length in 2..3 -> region = p.uppercase()
                }
            }
            return LocaleSpec(lang, region, script)
        }

        private fun buildTag(language: String, script: String?, region: String?): String {
            val sb = StringBuilder(language.lowercase())
            if (!script.isNullOrBlank()) sb.append("-").append(script.replaceFirstChar { it.uppercase() })
            if (!region.isNullOrBlank()) sb.append("-").append(region.uppercase())
            return sb.toString()
        }
    }
}

class LocaleChain(private val preferred: LocaleSpec, private val defaults: List<LocaleSpec> = listOf(LocaleSpec("en"))) {
    fun sequence(): List<String> {
        val seq = mutableListOf<String>()
        // ordem: lang-script-region → lang-region → lang-script → lang → defaults
        val l = preferred.language
        val s = preferred.script
        val r = preferred.region

        fun addIf(tag: String?) { if (!tag.isNullOrBlank()) seq.add(tag) }

        addIf(LocaleSpec.buildTag(l, s, r))
        addIf(LocaleSpec.buildTag(l, null, r))
        addIf(LocaleSpec.buildTag(l, s, null))
        addIf(LocaleSpec.buildTag(l, null, null))

        defaults.map { it.tag }.forEach { if (!seq.contains(it)) seq.add(it) }
        return seq.distinct()
    }
}
