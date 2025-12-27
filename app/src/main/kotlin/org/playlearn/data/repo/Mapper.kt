package org.playlearn.data.repo

import com.squareup.moshi.Moshi
import org.playlearn.core.model.Content
import org.playlearn.core.model.ContentStatus
import org.playlearn.core.model.Preferences
import org.playlearn.core.model.User
import org.playlearn.data.local.ContentEntity
import org.playlearn.data.local.PreferencesEntity
import org.playlearn.data.local.UserEntity

object Mapper {
    private val moshi = Moshi.Builder().build()
    private val adapter = moshi.adapter<Map<String, String>>(Map::class.java)

    fun toEntity(user: User) = UserEntity(user.id, user.name, user.age, user.locale)
    fun fromEntity(user: UserEntity) = User(user.id, user.name, user.age, user.locale)

    fun toEntity(content: Content): ContentEntity = ContentEntity(
        id = content.id,
        title = content.title,
        level = content.level,
        status = content.status.name,
        metadataJson = adapter.toJson(content.metadata)
    )

    fun fromEntity(entity: ContentEntity): Content = Content(
        id = entity.id,
        title = entity.title,
        level = entity.level,
        status = ContentStatus.valueOf(entity.status),
        metadata = adapter.fromJson(entity.metadataJson) ?: emptyMap()
    )

    fun toEntity(prefs: Preferences) = PreferencesEntity(
        locale = prefs.locale,
        darkTheme = prefs.darkTheme,
        notificationsEnabled = prefs.notificationsEnabled
    )

    fun fromEntity(entity: PreferencesEntity) = Preferences(
        locale = entity.locale,
        darkTheme = entity.darkTheme,
        notificationsEnabled = entity.notificationsEnabled
    )
}
