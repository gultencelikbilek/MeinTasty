package com.example.meintasty.feature.login_screen

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.usecase.InsertUserUseCase
import com.example.meintasty.domain.usecase.LoginUserUseCase
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.login_model.login_response.LoginUser
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun insertLoginUser(loginUserRequest: LoginUserRequest) {
        viewModelScope.launch {
            loginUserUseCase.invoke(loginUserRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _loginState.value = LoginState(
                            data = null,
                            isSuccess = false,
                            isLoading = false,
                            isError = result.msg
                        )
                        Log.d("loginviewmodel:", "${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _loginState.value = LoginState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _loginState.value = LoginState(
                            data = result.data.value,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                        result.data.let { response ->
                            val userAccountModel = UserAccountModel(
                                id = 0,
                                userId = response.value.userId,
                                fullName = response.value.fullName,
                                roleList = response.value.roleList,
                                token = response.value.token
                            )
                            Log.d("LoginViewModel", " ${response.value.token}")
                              insertUserUseCase.invoke(userAccountModel)
                            Log.d("LoginViewModel", "insertUser called with: ${response.value.token}")
                        }
                    }
                }
            }
        }
    }
}

data class LoginState(
    val data: LoginUser? = null,
    val isSuccess : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError : String?= ""
)