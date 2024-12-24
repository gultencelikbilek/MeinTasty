package com.example.meintasty.feature.user_feature.update_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.user_model_.update_email_model.update_email_request.EmailUpdateRequest
import com.example.meintasty.domain.model.user_model_.update_email_model.update_email_response.EmailUpdateResponse
import com.example.meintasty.domain.model.user_model_.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.domain.model.user_model_.update_phone_model.update_phone_response.UpdatePhoneResponse
import com.example.meintasty.domain.usecase.user_usecase.EmailUpdateUseCase
import com.example.meintasty.domain.usecase.user_usecase.UpdatePhoneUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updatePhoneUseCase: UpdatePhoneUseCase,
    private val emailUpdateUseCase: EmailUpdateUseCase
) : ViewModel() {

    private val _updatePhoneState = MutableStateFlow(UpdatePhoneState())
    val updatePhoneState = _updatePhoneState.asStateFlow()

    private val _updateEmailState = MutableStateFlow(UpdateEmailState())
    val updateEmailState = _updateEmailState.asStateFlow()

    fun updatePhone(updatePhoneRequest: UpdatePhoneRequest) {
        viewModelScope.launch {
            updatePhoneUseCase.invoke(updatePhoneRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _updatePhoneState.value = UpdatePhoneState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                    }
                    NetworkResult.Loading -> {
                        _updatePhoneState.value = UpdatePhoneState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _updatePhoneState.value = UpdatePhoneState(
                            data = result.data,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                    }
                }
            }
        }
    }

    fun updateEmail(emailUpdateRequest: EmailUpdateRequest){
        viewModelScope.launch {
            emailUpdateUseCase.invoke(emailUpdateRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _updateEmailState.value = UpdateEmailState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                    }
                    NetworkResult.Loading -> {
                        _updateEmailState.value = UpdateEmailState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _updateEmailState.value = UpdateEmailState(
                            data = result.data,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                    }
                }
            }
        }
    }
}

data class UpdatePhoneState(
    val data: UpdatePhoneResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class UpdateEmailState(
    val data: EmailUpdateResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)