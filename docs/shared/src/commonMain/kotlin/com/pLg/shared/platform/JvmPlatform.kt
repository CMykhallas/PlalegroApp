package com.pLg.shared.domain

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

public actual object IdGenerator {
    public actual fun uuid(): String = UUID.randomUUID().toString()
}

public actual object Clock {
    public actual fun nowIso(): String = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
}
