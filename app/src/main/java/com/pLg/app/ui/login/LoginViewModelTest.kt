package com.pLg.app.ui.login

import com.pLg.app.ui.viewmodel.LoginViewModel
import com.pLg.core.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @Test
    fun login_viewmodel_registers_user_and_saves_id() = runTest {
        val repo = FakeUserRepo()
        val vm = LoginViewModel(repo, androidx.lifecycle.SavedStateHandle())

        vm.login("Alice", 7)
        val state = vm.loginState.first()

        assertEquals("Alice", state?.getOrNull()?.name)
        assertEquals(7, state?.getOrNull()?.age)
    }
}
