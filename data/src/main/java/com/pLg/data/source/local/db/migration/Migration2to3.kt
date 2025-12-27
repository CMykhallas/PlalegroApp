package com.plg.source.local.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Migration 2 → 3
 * Cria nova tabela 'content_states'.
 */
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS content_states (
                id TEXT PRIMARY KEY NOT NULL,
                lessonId TEXT NOT NULL,
                state TEXT NOT NULL
            )
            """.trimIndent()
        )
    }
}
