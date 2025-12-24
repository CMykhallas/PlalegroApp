package com.pLg.data.util

data class PageRequest(val page: Int, val size: Int) {
    init {
        require(page >= 0) { "page >= 0" }
        require(size in 1..200) { "size in 1..200" }
    }
}
data class Page<T>(
    val items: List<T>,
    val page: Int,
    val size: Int,
    val total: Long
)
