package com.pLg.core.i18n

/**
 * Resolve mensagens com fallback, interpolação e pluralização.
 *
 * Suporta:
 * - Placeholders simples: {name}
 * - Plural: {count, plural, =0 {...} one {...} other {...}}
 * - Substituição de '#' pela contagem no bloco plural
 */
class MessageResolver(
    private val catalog: MessageCatalog,
    private val localeProvider: LocaleProvider,
    private val defaults: List<LocaleSpec> = listOf(LocaleSpec("en"))
) {

    fun resolve(key: MessageKey, args: Map<String, Any?> = emptyMap()): String {
        val chain = LocaleChain(localeProvider.current(), defaults).sequence()
        val raw = chain.asSequence()
            .mapNotNull { catalog.get(it, key) }
            .firstOrNull() ?: throw I18nError.MissingKey(key, chain)

        val chosenLocaleTag = chain.first()
        return format(raw, chosenLocaleTag, args)
    }

    private fun format(template: String, localeTag: String, args: Map<String, Any?>): String {
        var result = template

        // Plural blocks
        result = result.replace(Regex("""\{(\w+),\s*plural,\s*([^}]+)}""")) { match ->
            val varName = match.groupValues[1]
            val rulesBody = match.groupValues[2]
            val n = (args[varName] as? Number)?.toInt()
                ?: throw I18nError.InvalidTemplate("Variável de plural '$varName' ausente ou não numérica")

            val lang = LocaleSpec.parse(localeTag).language
            val cat = PluralRules.categoryFor(lang, n)

            val options = Regex("""(=?\w+)\s*\{([^}]*)}""")
                .findAll(rulesBody)
                .associate { it.groupValues[1] to it.groupValues[2] }

            val exactKey = "=$n"
            val chosen = when {
                options.containsKey(exactKey) -> options.getValue(exactKey)
                cat == PluralRules.Category.ONE && options.containsKey("one") -> options.getValue("one")
                options.containsKey("other") -> options.getValue("other")
                else -> throw I18nError.InvalidTemplate("Bloco plural sem opção aplicável: $rulesBody")
            }

            chosen.replace("#", n.toString())
        }

        // Placeholders simples
        result = result.replace(Regex("""\{(\w+)}""")) { m ->
            val k = m.groupValues[1]
            val v = args[k]
            when (v) {
                null -> ""
                is Number -> Formatters.number(localeTag, v)
                else -> v.toString()
            }
        }

        return result
    }
}
