package com.pLg.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import com.pLg.core.model.User
import com.pLg.data.repo.contract.UserRepository

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<User>?>(null)
    val loginState: StateFlow<Result<User>?> = _loginState

    fun login(name: String, age: Int) {
        viewModelScope.launch {
            val user = User(
                id = UUID.randomUUID().toString(),
                name = name,
                age = age,
                locale = "pt-MZ"
            )
            val result = userRepo.register(user)
            savedStateHandle["userId"] = user.id
            _loginState.value = result
        }
    }
}
