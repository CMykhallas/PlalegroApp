package com.plg.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plg.source.local.db.dao.ChildProfileDao
import com.plg.source.local.db.dao.LessonDao
import com.plg.source.local.db.dao.ProgressDao
import com.plg.source.local.db.dao.ContentStateDao
import com.plg.source.local.db.entity.ChildProfileEntity
import com.plg.source.local.db.entity.LessonEntity
import com.plg.source.local.db.entity.ProgressEntity
import com.plg.source.local.db.entity.ContentStateEntity

/**
 * AppDatabase
 * Banco de dados principal da aplicação usando Room.
 * Integra todas as entidades e DAOs, com suporte a migrations e type converters.
 */
@Database(
    entities = [
        ChildProfileEntity::class,
        LessonEntity::class,
        ProgressEntity::class,
        ContentStateEntity::class
    ],
    version = 3, // sempre atualizar ao criar migrations
    exportSchema = true
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    // DAOs expostos
    abstract fun childProfileDao(): ChildProfileDao
    abstract fun lessonDao(): LessonDao
    abstract fun progressDao(): ProgressDao
    abstract fun contentStateDao(): ContentStateDao

    companion object {
        const val DATABASE_NAME = "play_learn_grow_db"
    }
}
