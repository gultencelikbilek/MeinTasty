package com.example.meintasty.feature.signup_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.usecase.SignUpUseCase
import com.example.meintasty.domain.model.signup_model.signup_response.Signup
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
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
                    }
                }
           /* val response = signUpRepositoryImpl.signUp(signupRequest)
            response.value.let {
                _signUpState.value = SignUpState(
                    data = response.value,
                    success = response.success
                )*/
                //signup yapınca token dönmüyor !!!!!!
              //val userAccountModel = UserAccountModel(
              //    0,
              //    fullName = response.value.fullName,
              //    roleList = emptyList(),
              //    token = response.value.
              //)
              //loginUserRepositoryImpl.insertToken()
              //Log.d("response:value","$response")
            }
        }
    }
}

data class SignUpState(
    val data: Signup? = null,
    val isSuccess : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError:  String? = ""
)