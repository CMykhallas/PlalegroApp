package org.playlearn.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.playlearn.core.model.User
import org.playlearn.core.util.AppResult
import org.playlearn.domain.repo.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val user = MutableStateFlow<User?>(null)
    val error = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            when (val result = userRepository.currentUser()) {
                is AppResult.Success -> user.value = result.data
                is AppResult.Error -> error.value = result.throwable.message
                else -> {}
            }
        }
    }
}
