package com.example.meintasty.feature.detail_restaurant

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.usecase.BasketUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(
    private val restaurantDetailUseCase: RestaurantDetailUseCase,
    private val basketUseCase: BasketUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase
) : ViewModel() {

    private val _detailRestState = MutableStateFlow(DetailRestaurantState())
    val detailRestState = _detailRestState.asStateFlow()

    private val _userModelState = MutableStateFlow(UserModelState())
    val userModelState = _userModelState.asStateFlow()

    private val _addBasketState = MutableStateFlow(AddBasketState())
    val addBasketState = _addBasketState.asStateFlow()

    init {
        getUserModel()
    }

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

    fun getUserModel() {
        viewModelScope.launch {
            val response = getUserDatabaseUseCase.invoke()
            _userModelState.value = UserModelState(
                data = response
            )
        }
    }

    fun addBasket(addBasketRequest: AddBasketRequest) {
        viewModelScope.launch {
            basketUseCase.addBasketUseCase(addBasketRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _addBasketState.value = AddBasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                    }

                    NetworkResult.Loading -> {
                        _addBasketState.value = AddBasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        _addBasketState.value = AddBasketState(
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

data class DetailRestaurantState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class AddBasketState(
    val data: AddBasketResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class UserModelState(
    val data: UserAccountModel? = null
)