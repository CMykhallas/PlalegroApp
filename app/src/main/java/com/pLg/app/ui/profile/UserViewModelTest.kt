package com.pLg.app.ui.profile

import com.pLg.app.ui.viewmodel.UserViewModel
import com.pLg.core.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @Test
    fun user_viewmodel_loads_current_user() = runTest {
        val repo = FakeUserRepo()
        val vm = UserViewModel(repo, androidx.lifecycle.SavedStateHandle())

        val user = vm.user.first()
        assertEquals("Alice", user?.name)
        assertEquals(7, user?.age)
    }
}
