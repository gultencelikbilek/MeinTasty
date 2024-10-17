package com.example.meintasty.feature.password_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.user_password_model.user_pasword_request.UpdatePasswordRequest
import com.example.meintasty.domain.model.user_password_model.user_pasword_response.UpdatePasswordResponse
import com.example.meintasty.domain.usecase.UpdatePasswordUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel  @Inject constructor(
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : ViewModel(){

    private val _passwordState = MutableStateFlow(PasswordState())
    val passwwordState= _passwordState.asStateFlow()


    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest){
        viewModelScope.launch {
            updatePasswordUseCase.invoke(updatePasswordRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _passwordState.value = PasswordState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("passwordViewModel:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _passwordState.value = PasswordState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError =""
                        )
                    }
                    is NetworkResult.Success -> {
                        _passwordState.value = PasswordState(
                            data = result.data,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                        Log.d("passwordViewModel:","${result.data}")
                    }
                }
            }
        }
    }


}

data class PasswordState(
    val data : UpdatePasswordResponse? = null,
    val isSuccess : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError : String? = "",
)