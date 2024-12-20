package com.example.meintasty.feature.user_feature.detail_restaurant

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.RestaurantAccountModel
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasket
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.usecase.AddBasketUseCase
import com.example.meintasty.domain.usecase.GetBasketUseCase
import com.example.meintasty.domain.usecase.GetRestaurantTokenUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.feature.NetworkResult
import com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen.RestaurantDatabaseState
import com.example.meintasty.feature.user_feature.basket_screen.BasketState
import com.example.meintasty.feature.user_feature.basket_screen.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(
    private val restaurantDetailUseCase: RestaurantDetailUseCase,
    private val addBasketUseCase: AddBasketUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase,
    private val getBasketUseCase: GetBasketUseCase,

    ) : ViewModel() {

    private val _detailRestState = MutableStateFlow(DetailRestaurantState())
    val detailRestState = _detailRestState.asStateFlow()


    private val _userModelState = MutableStateFlow(UserModelState())
    val userModelState = _userModelState.asStateFlow()

    private val _addBasketState = MutableStateFlow(AddBasketState())
    val addBasketState = _addBasketState.asStateFlow()

    private val _basketRestIdControlState = MutableStateFlow(BasketRestIdControlState())
    val basketRestIdControlState = _basketRestIdControlState.asStateFlow()


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
                                isSuccess = true,
                                isLoading = false,
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
            addBasketUseCase.invoke(addBasketRequest).collect {
                when (it) {
                    is NetworkResult.Failure -> {
                        _addBasketState.value = AddBasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = it.msg
                        )
                        Log.d("addBasketState:error:", "${it.msg}")
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
                            data = it.data.value,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )

                        Log.d("addBasketState:succes", "${it.data.value}")
                    }
                }
            }
        }
    }

    fun getBasket(basketRequest: GetBasketRequest) {
        viewModelScope.launch {
            getBasketUseCase.invoke(getBasketRequest = basketRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _basketRestIdControlState.value = BasketRestIdControlState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("basketViewmodel:Error:", "${result.msg}")
                    }

                    NetworkResult.Loading -> {
                        _basketRestIdControlState.value = BasketRestIdControlState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        val basketData = result.data.value
                        Log.d("API Response:", "$basketData")
                        _basketRestIdControlState.value = BasketRestIdControlState(
                            data = basketData,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                       // updateTotalPrice()
                        Log.d("basketViewmodel:success:", "${basketData}")

                    }
                }
            }
        }
    }
}

data class AddBasketState(
    val data: AddBasket? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class DetailRestaurantState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)


data class UserModelState(
    val data: UserAccountModel? = null
)
data class BasketRestIdControlState(
    val data: List<Basket?>? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)
