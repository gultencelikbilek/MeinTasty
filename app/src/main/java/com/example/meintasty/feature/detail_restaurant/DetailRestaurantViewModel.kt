package com.example.meintasty.feature.detail_restaurant

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(
    private val restaurantDetailUseCase: RestaurantDetailUseCase
) : ViewModel() {

    private val _detailRestState = MutableStateFlow(DetailRestaurantState())
    val detailRestState = _detailRestState.asStateFlow()

    fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) {
        viewModelScope.launch {
            restaurantDetailUseCase.invoke(detailRestaurantRequest)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _detailRestState.value = DetailRestaurantState(
                                data = null,
                                isSuccess = false,
                                isLoading = false,
                                isError = result.msg
                            )
                            Log.d("networkState:error:", result.msg)
                        }

                        is NetworkResult.Success -> {
                            _detailRestState.value = DetailRestaurantState(
                                data = result.data.value,
                                isSuccess = true,
                                isLoading = false,
                                isError = ""
                            )
                            Log.d("networkState:succes:", result.data.value.toString())
                        }

                        NetworkResult.Loading -> {
                            _detailRestState.value = DetailRestaurantState(
                                data = null,
                                isSuccess = false,
                                isLoading = true,
                                isError = null
                            )
                        }
                    }
                }
        }
    }
}

data class DetailRestaurantState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)