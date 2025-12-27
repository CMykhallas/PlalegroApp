package org.playlearn.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.playlearn.core.model.User
import org.playlearn.core.util.AppResult
import org.playlearn.domain.usecase.LoginUserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUser: LoginUserUseCase
) : ViewModel() {

    val state = MutableStateFlow<AppResult<User>?>(null)

    fun login(name: String, age: Int) {
        state.value = AppResult.Loading
        viewModelScope.launch {
            state.value = loginUser(name, age)
        }
    }
}
