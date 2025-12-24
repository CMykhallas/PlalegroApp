package com.pLg.app.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pLg.app.core.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        refreshConnectivity()
    }

    fun refreshConnectivity() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isOnline = networkMonitor.isOnline())
        }
    }
}

data class HomeUiState(
    val isOnline: Boolean = true
)
