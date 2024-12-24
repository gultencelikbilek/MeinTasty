package com.example.meintasty.feature.user_feature.signup_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.usecase.user_usecase.SignUpUseCase
import com.example.meintasty.domain.model.user_model_.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.user_model_.signup_model.signup_response.SignUp
import com.example.meintasty.domain.usecase.user_usecase.InsertUserTokenUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val insertTokenUseCase: InsertUserTokenUseCase
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
                        result.data?.let {response ->
                            if (response.value?.token != null){
                                val userAccountModel = UserAccountModel(
                                    0,
                                    userId =  response.value.userId,
                                    fullName = response.value.fullName,
                                    roleList = response.value.roleList,
                                    token = response.value.token
                                )
                                insertTokenUseCase.invoke(userAccountModel)
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