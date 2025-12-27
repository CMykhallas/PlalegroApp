package com.plg.source.local.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Migration 1 → 2
 * Adiciona coluna 'nickname' à tabela child_profiles.
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE child_profiles ADD COLUMN nickname TEXT")
    }
}
