package com.pLg.core.utils

object CollectionUtils {
    fun <T> isNullOrEmpty(collection: Collection<T>?): Boolean = collection == null || collection.isEmpty()

    fun <T> nonEmptyOrNull(collection: Collection<T>?): Collection<T>? =
        if (collection.isNullOrEmpty()) null else collection

    fun <T> firstOrNull(collection: Collection<T>, predicate: (T) -> Boolean): T? =
        collection.firstOrNull(predicate)

    fun <T> mergeDistinct(a: Collection<T>, b: Collection<T>): Set<T> =
        (a + b).toSet()
}
