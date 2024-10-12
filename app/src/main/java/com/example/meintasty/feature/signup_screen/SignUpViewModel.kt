package com.example.meintasty.feature.signup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.LoginUserRepositoryImpl
import com.example.meintasty.data.repoimpl.SignUpRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.signup_model.Signup
import com.example.meintasty.domain.model.signup_model.SignupRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepositoryImpl: SignUpRepositoryImpl,
    private val loginUserRepositoryImpl: LoginUserRepositoryImpl
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signupState = _signUpState.asStateFlow()

    fun signUp(signupRequest: SignupRequest) {
        viewModelScope.launch {
            val response = signUpRepositoryImpl.signUp(signupRequest)
            response.value.let {
                _signUpState.value = SignUpState(
                    data = response.value
                )
            }
           // val userAccountModel = UserAccountModel(0,response.value.fullName)
          //  loginUserRepositoryImpl.getToken(re)
        }
    }
}

data class SignUpState(
    val data: Signup? = null
)