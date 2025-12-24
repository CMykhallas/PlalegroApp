package com.pLg.core.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {
    private val isoDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val isoDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun now(): LocalDateTime = LocalDateTime.now()

    fun today(): LocalDate = LocalDate.now()

    fun formatDate(date: LocalDate): String = isoDateFormatter.format(date)

    fun formatDateTime(dateTime: LocalDateTime): String = isoDateTimeFormatter.format(dateTime)

    fun parseDate(raw: String): LocalDate? = runCatching { LocalDate.parse(raw, isoDateFormatter) }.getOrNull()

    fun parseDateTime(raw: String): LocalDateTime? = runCatching { LocalDateTime.parse(raw, isoDateTimeFormatter) }.getOrNull()
}
