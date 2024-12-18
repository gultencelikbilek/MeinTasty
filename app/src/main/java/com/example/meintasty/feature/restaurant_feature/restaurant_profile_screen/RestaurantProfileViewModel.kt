package com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.RestaurantAccountModel
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.model.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.domain.usecase.GetRestaurantTokenUseCase
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.usecase.UpdateMenuUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantProfileViewModel @Inject constructor(
    private val getRestaurantTokenUseCase: GetRestaurantTokenUseCase,
    private val restaurantDetailUseCase: RestaurantDetailUseCase,
    private val updateMenuUseCase: UpdateMenuUseCase
) : ViewModel() {
    private val _restaurantDatabaseState = MutableStateFlow(RestaurantDatabaseState())
    val restaurantDatabaseState = _restaurantDatabaseState.asStateFlow()

    private val _detailRestaurantProfileState = MutableStateFlow(RestaurantDetailState())
    val detailRestaurantProfileState = _detailRestaurantProfileState.asStateFlow()




    fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) {
        viewModelScope.launch {
            restaurantDetailUseCase.invoke(detailRestaurantRequest)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _detailRestaurantProfileState.value = RestaurantDetailState(
                                data = null,
                                isSuccess = false,
                                isLoading = false,
                                isError = result.msg
                            )
                            Log.d("restaurantProfileViewmodel:error:", result.msg)
                        }

                        NetworkResult.Loading -> {
                            _detailRestaurantProfileState.value = RestaurantDetailState(
                                data = null,
                                isSuccess = true,
                                isLoading = false,
                                isError = null
                            )
                        }

                        is NetworkResult.Success -> {
                            val updatedMenuList = result.data.value?.menuList
                            Log.d("updateMneuList","$updatedMenuList")
                            _detailRestaurantProfileState.value = RestaurantDetailState(
                                data = result.data.value?.copy(menuList = updatedMenuList),
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
