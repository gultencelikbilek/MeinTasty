package com.example.meintasty.feature.user_feature.user_profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.user_model_.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.model.user_model_.get_user_model.user_response.UserResponse
import com.example.meintasty.domain.usecase.user_usecase.GetUserUseCase
import com.example.meintasty.domain.usecase.user_usecase.GetUserDatabaseUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase,
    ) : ViewModel() {

        private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    private val _userDatabaseState = MutableStateFlow(UserDatabaseState())
    val userDatabaseState = _userDatabaseState.asStateFlow()

    fun getUser(userRequest: UserRequest){
        viewModelScope.launch {
            getUserUseCase.invoke(userRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _userState.value = UserState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("usrviewmodel:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _userState.value = UserState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _userState.value = UserState(
                            data = result.data,
                            isSucces = true,
                            isLoading = false,
                            isError =""
                        )
                        Log.d("usrviewmodel:","${result.data}")
                    }
                }
            }
        }
    }

    fun getUserDatabaseModel() {
        viewModelScope.launch {
            val response = getUserDatabaseUseCase.invoke()
            Log.d("userdatabase:",  "$response")
            response.userId?.let {
                Log.d("userrequest:userId1:",  "${it}")
                if (it != null && it > 0) {
                    Log.d("userrequest:userId2::",  "${it}")
                    _userDatabaseState.value = UserDatabaseState(
                        data = response
                    )
                }
            }
        }
    }
}


data class UserState(
    val data : UserResponse? = null,
    val isSucces : Boolean? = false,
    val isLoading: Boolean? = false,
    val isError : String? = ""
)
data class UserDatabaseState(
    val data: UserAccountModel? = null
)


