package com.example.meintasty.feature.restaurant_feature.restaurant_login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.RestaurantAccountModel
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_request.RestaurantLoginRequest
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_response.RestaurantLoginModel
import com.example.meintasty.domain.usecase.InsertRestaurantTokenUseCase
import com.example.meintasty.domain.usecase.RestaurantLoginUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantLoginViewModel @Inject constructor(
    private val restaurantLoginUseCase: RestaurantLoginUseCase,
    private val insertRestaurantTokenUseCase: InsertRestaurantTokenUseCase
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
                            isSucces = true,
                            isLoading = false,
                            isError = ""
                        )
                        Log.d("restaurantLoginViewModel:success:","${result.data.value}")

                        result.data.let {response ->
                            Log.d("ResponseCheck", "Token: ${response.value?.token}, FullName: ${response.value?.fullName}")
                            if (response.value?.token != null){
                                val restaurantAccountModel = RestaurantAccountModel(
                                    id = 0,
                                    restaurantId = response.value.restaurantId,
                                    fullName = response.value.fullName,
                                    roleList = response.value.roleList,
                                    token = response.value.token,
                                    isRestaurant = true
                                )
                                Log.d("RestaurantLoginViewModel", " ${response.value.token}")
                                insertRestaurantTokenUseCase.invoke(restaurantAccountModel)
                                Log.d("RestaurantLoginViewModel", "insertRestaurant called with: ${restaurantAccountModel.token}")
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
