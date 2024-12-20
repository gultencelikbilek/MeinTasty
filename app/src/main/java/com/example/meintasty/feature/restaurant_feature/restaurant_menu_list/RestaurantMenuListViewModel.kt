package com.example.meintasty.feature.restaurant_feature.restaurant_menu_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.RestaurantAccountModel
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.usecase.GetRestaurantTokenUseCase
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantMenuListViewModel @Inject constructor(
    private val getRestaurantTokenUseCase: GetRestaurantTokenUseCase,
    private val restaurantDetailUseCase: RestaurantDetailUseCase
): ViewModel() {

    private val _restaurantMenuListDbState = MutableStateFlow(RestaurantDatabaseMenuListState())
    val restaurantMenuListDbState = _restaurantMenuListDbState.asStateFlow()

    private val _restaurantMenuListState = MutableStateFlow(RestaurantDetailMenuListState())
    val restaurantMenuListState = _restaurantMenuListState.asStateFlow()

    fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) {
        viewModelScope.launch {
            restaurantDetailUseCase.invoke(detailRestaurantRequest)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _restaurantMenuListState.value = RestaurantDetailMenuListState(
                                data = null,
                                isSuccess = false,
                                isLoading = false,
                                isError = result.msg
                            )
                            Log.d("restaurantProfileViewmodel:error:", result.msg)
                        }

                        NetworkResult.Loading -> {
                            _restaurantMenuListState.value = RestaurantDetailMenuListState(
                                data = null,
                                isSuccess = true,
                                isLoading = false,
                                isError = null
                            )
                        }

                        is NetworkResult.Success -> {
                            val updatedMenuList = result.data.value?.menuList
                            Log.d("updateMneuList","$updatedMenuList")
                            _restaurantMenuListState.value = RestaurantDetailMenuListState(
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
                        _restaurantMenuListDbState.value = RestaurantDatabaseMenuListState(
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
data class RestaurantDatabaseMenuListState(
    val data: RestaurantAccountModel? = null
)

data class RestaurantDetailMenuListState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)
