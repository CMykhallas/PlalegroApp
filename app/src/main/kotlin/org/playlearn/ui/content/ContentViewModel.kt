package org.playlearn.ui.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.playlearn.core.model.Content
import org.playlearn.domain.usecase.GetContentsUseCase
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getContents: GetContentsUseCase
) : ViewModel() {

    private val _contents = MutableStateFlow<List<Content>>(emptyList())
    val contents: StateFlow<List<Content>> = _contents

    init {
        viewModelScope.launch {
            getContents().collect { _contents.value = it }
        }
    }

    fun refresh() {
        viewModelScope.launch { getContents.refresh() }
    }
}
