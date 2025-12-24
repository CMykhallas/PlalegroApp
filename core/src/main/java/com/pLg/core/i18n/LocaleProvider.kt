package com.pLg.core.i18n

import java.util.concurrent.atomic.AtomicReference

/**
 * Fonte do locale atual (mutável e thread-safe).
 */
interface LocaleProvider {
    fun current(): LocaleSpec
}

class MutableLocaleProvider(initial: LocaleSpec = LocaleSpec("pt", "MZ")) : LocaleProvider {
    private val ref = AtomicReference(initial)
    override fun current(): LocaleSpec = ref.get()
    fun set(value: LocaleSpec) = ref.set(value)
}
