package org.playlearn.domain.usecase

import org.playlearn.core.model.Preferences
import org.playlearn.core.util.AppResult
import org.playlearn.domain.repo.PreferencesRepository
import javax.inject.Inject

class UpdatePreferencesUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(prefs: Preferences): AppResult<Unit> =
        preferencesRepository.update(prefs)
}
