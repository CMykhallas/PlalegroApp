@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val user: Flow<User?> = flow {
        val id: String? = savedStateHandle["userId"]
        val result = if (id != null) repo.getUserById(id) else repo.currentUser()
        emit(result.getOrNull())
    }
}
package com.pLg.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.pLg.core.model.User
import com.pLg.data.repo.contract.UserRepository

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            val id: String? = savedStateHandle["userId"]
            val result = if (id != null) repo.getUserById(id) else repo.currentUser()
            _user.value = result.getOrNull()
        }
    }
}
