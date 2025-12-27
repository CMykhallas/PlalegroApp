package org.playlearn.ui.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.playlearn.core.model.Preferences
import org.playlearn.core.util.AppResult
import org.playlearn.domain.repo.PreferencesRepository
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val prefs = MutableStateFlow(Preferences())
    val saving = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            when (val res = preferencesRepository.get()) {
                is AppResult.Success -> prefs.value = res.data
                else -> {}
            }
        }
    }

    fun updateLocale(locale: String) {
        prefs.value = prefs.value.copy(locale = locale)
    }

    fun toggleDarkTheme() {
        prefs.value = prefs.value.copy(darkTheme = !prefs.value.darkTheme)
    }

    fun save() {
        viewModelScope.launch {
            saving.value = true
            preferencesRepository.update(prefs.value)
            saving.value = false
        }
    }
}
