package com.example.meintasty.feature.user_feature.user_login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.usecase.user_usecase.InsertUserTokenUseCase
import com.example.meintasty.domain.usecase.user_usecase.LoginUserUseCase
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.user_model_.login_model.login_response.LoginUser
import com.example.meintasty.domain.model.user_model_.login_model.login_request.LoginUserRequest
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val insertUserTokenUseCase: InsertUserTokenUseCase
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
                            if (response.value.token != null){
                                    val userAccountModel = UserAccountModel(
                                        id = 0,
                                        userId = response.value.userId,
                                        fullName = response.value.fullName,
                                        roleList = response.value.roleList,
                                        token = response.value.token,
                                        isUser = true
                                    )
                                    Log.d("LoginViewModel", " ${response.value.token}")
                                    insertUserTokenUseCase.invoke(userAccountModel)
                                    Log.d("LoginViewModel", "insertUser called with: ${response.value.token}")
                            }
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