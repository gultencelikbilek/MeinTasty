package com.example.meintasty.feature.restaurant_login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.login_model.login_response.LoginUser
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_request.RestaurantLoginRequest
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_response.RestaurantLoginModel
import com.example.meintasty.domain.usecase.InsertTokenUseCase
import com.example.meintasty.domain.usecase.RestaurantLoginUseCase
import com.example.meintasty.feature.NetworkResult
import com.example.meintasty.feature.user_login_screen.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantLoginViewModel @Inject constructor(
    private val restaurantLoginUseCase: RestaurantLoginUseCase,
    private val insertTokenUseCase: InsertTokenUseCase
) : ViewModel(){


    private val _restaurantLoginState = MutableStateFlow(RestaurantLoginState())
    val restaurantLoginState = _restaurantLoginState.asStateFlow()

    fun restaurantLogin(restaurantLoginRequest: RestaurantLoginRequest){
        viewModelScope.launch {
            restaurantLoginUseCase.invoke(restaurantLoginRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure ->{
                        _restaurantLoginState.value = RestaurantLoginState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("restaurantLoginViewModel:error:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _restaurantLoginState.value = RestaurantLoginState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError =   ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _restaurantLoginState.value = RestaurantLoginState(
                            data = result.data.value,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                        result.data.value?.let {response ->
                            if (response.token != null){
                                val userAccountModel = UserAccountModel(
                                    id = 0,
                                    userId = 0,
                                    restaurantId = response.restaurantId,
                                    fullName = response.fullName,
                                    roleList = response.roleList,
                                    token = response.token
                                )
                                insertTokenUseCase.invoke(userAccountModel)
                                Log.d("restaurantLoginViewModel:success:","${userAccountModel}")

                            }
                        }
                        Log.d("restaurantLoginViewModel:success:","${result.data.value}")

                    }
                }

            }
        }
    }

}

data class RestaurantLoginState(
    val data : RestaurantLoginModel? = null,
    val isSucces : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError : String? = "",
)
