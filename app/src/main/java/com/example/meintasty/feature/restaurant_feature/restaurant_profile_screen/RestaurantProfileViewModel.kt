package com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.RestaurantAccountModel
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.usecase.GetRestaurantTokenUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.feature.NetworkResult
import com.example.meintasty.feature.user_feature.detail_restaurant.DetailRestaurantState
import com.example.meintasty.feature.user_feature.user_profile_screen.UserDatabaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantProfileViewModel @Inject constructor(
    private val getRestaurantTokenUseCase: GetRestaurantTokenUseCase,
    private val restaurantDetailUseCase: RestaurantDetailUseCase,
) : ViewModel() {
    private val _restaurantDatabaseState = MutableStateFlow(RestaurantDatabaseState())
    val restaurantDatabaseState = _restaurantDatabaseState.asStateFlow()

    private val _detailRestProfState = MutableStateFlow(RestaurantDetailState())
    val detailRestProfState = _detailRestProfState.asStateFlow()

    fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) {
        viewModelScope.launch {
            restaurantDetailUseCase.invoke(detailRestaurantRequest)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _detailRestProfState.value = RestaurantDetailState(
                                data = null,
                                isSuccess = false,
                                isLoading = false,
                                isError = result.msg
                            )
                            Log.d("restaurantProfileViewmodel:error:", result.msg)
                        }

                        NetworkResult.Loading -> {
                            _detailRestProfState.value = RestaurantDetailState(
                                data = null,
                                isSuccess = true,
                                isLoading = false,
                                isError = null
                            )
                        }

                        is NetworkResult.Success -> {
                            _detailRestProfState.value = RestaurantDetailState(
                                data = result.data.value,
                                isSuccess = true,
                                isLoading = false,
                                isError = ""
                            )
                            Log.d(
                                "restaurantProfileViewmodel:succes:",
                                result.data.value.toString()
                            )
                        }
                    }
                }
        }
    }

    fun getRestaurantDatabase() {
        viewModelScope.launch {
            val response = getRestaurantTokenUseCase.invoke()
            if (response != null) {
                Log.d("userdatabase:", "$response")
                response.restaurantId?.let {
                    Log.d("userrequest:userId1:", "$it")
                    if (it > 0) {
                        Log.d("userrequest:userId2::", "$it")
                        _restaurantDatabaseState.value = RestaurantDatabaseState(
                            data = response
                        )
                        Log.d("userrequest:userId2::", "$response")

                    }
                }
            } else {
                Log.e("userdatabase", "Response is null!")
            }
        }
    }
}


data class RestaurantDatabaseState(
    val data: RestaurantAccountModel? = null
)

data class RestaurantDetailState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)
