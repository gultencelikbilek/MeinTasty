package com.example.meintasty.feature.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.LoginUserRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.login_model.LoginUser
import com.example.meintasty.domain.model.login_model.LoginUserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepositoryImpl: LoginUserRepositoryImpl,
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun loginFlowUser(loginUserRequest: LoginUserRequest) {
        viewModelScope.launch {
            val response = loginRepositoryImpl.login(loginUserRequest)
            if (response.success) {
                response.value.let {
                    val userAccountModel = UserAccountModel(
                        id = 0,
                        fullName = response.value.fullName,
                        roleList = response.value.roleList,
                        token = response.value.token
                    )
                    loginRepositoryImpl.insertToken(userAccountModel)
                    Log.d("LoginViewModel", "insertUser called with: ${response.value.token}")
                }
            }
            _loginState.value = LoginState(
                data = response.value
            )
        }
    }
}

data class LoginState(
    val data: LoginUser? = null
)