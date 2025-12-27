package org.playlearn.domain.repo

import org.playlearn.core.model.Preferences
import org.playlearn.core.util.AppResult

interface PreferencesRepository {
    suspend fun get(): AppResult<Preferences>
    suspend fun update(prefs: Preferences): AppResult<Unit>
}
