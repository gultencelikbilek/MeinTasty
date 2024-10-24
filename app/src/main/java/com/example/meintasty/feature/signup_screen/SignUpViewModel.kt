package com.example.meintasty.feature.signup_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.usecase.SignUpUseCase
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignUp
import com.example.meintasty.domain.usecase.InsertUserUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signupState = _signUpState.asStateFlow()

    fun signUp(signupRequest: SignupRequest) {
        viewModelScope.launch {
            signUpUseCase.invoke(signupRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _signUpState.value = SignUpState(
                            data = null,
                            isSuccess = false,
                            isLoading = false,
                            isError = result.msg
                        )
                        Log.d("signupviewmodel:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _signUpState.value = SignUpState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _signUpState.value = SignUpState(
                            data = result.data.value,
                            isSuccess = false,
                            isLoading = false,
                            isError = ""
                        )
                        Log.d("signupviewmodel:","${result}")
                        result.data.value.let {response ->
                        if (response?.token != null){
                                val userAccountModel = UserAccountModel(
                                    0,
                                    userId =  response.userId,
                                    fullName = response.fullName,
                                    roleList = response.roleList,
                                    token = response.token
                                )
                                insertUserUseCase.invoke(userAccountModel)
                                Log.d("useraccountModel:","$userAccountModel")

                        }
                        }
                    }
                }
            }
        }
    }
}

data class SignUpState(
    val data: SignUp? = null,
    val isSuccess : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError:  String? = ""
)