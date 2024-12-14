package com.example.meintasty.feature.restaurant_menu_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.feature.NetworkResult
import com.example.meintasty.feature.restaurant_profile_screen.RestaurantDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantMenuListViewModel @Inject constructor(
    private val restauratDetailUseCase: RestaurantDetailUseCase
) : ViewModel() {


    private val _detailMenuRestState = MutableStateFlow(RestaurantMenuDetailState())
    val detailMenuRestState = _detailMenuRestState.asStateFlow()

    fun menuDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) {
        viewModelScope.launch {
            restauratDetailUseCase.invoke(detailRestaurantRequest).collect {result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _detailMenuRestState.value = RestaurantMenuDetailState(
                            data = null,
                            isSuccess = false,
                            isLoading = false,
                            isError = result.msg
                        )
                        Log.d("restaurantProfileViewmodel:error:", result.msg)
                    }

                    NetworkResult.Loading -> {
                        _detailMenuRestState.value = RestaurantMenuDetailState(
                            data = null,
                            isSuccess = true,
                            isLoading = false,
                            isError = null
                        )
                    }

                    is NetworkResult.Success -> {
                        _detailMenuRestState.value = RestaurantMenuDetailState(
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
}

data class RestaurantMenuDetailState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)
