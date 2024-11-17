package com.example.meintasty.feature.basket_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.domain.model.update_basket_model.update_basket_request.UpdateBasketRequest
import com.example.meintasty.domain.model.update_basket_model.update_basket_response.UpdateBasket
import com.example.meintasty.domain.usecase.GetBasketUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.domain.usecase.RemoveBasketUseCase
import com.example.meintasty.domain.usecase.UpdateBasketUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketUseCase: GetBasketUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase,
    private val removeBasketUseCase: RemoveBasketUseCase,
    private val updateBasketUseCase :UpdateBasketUseCase
) : ViewModel() {

    private val _basketState = MutableStateFlow(BasketState())
    val basketState = _basketState.asStateFlow()

    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    private val _removeBasketState = MutableStateFlow(RemoveBasketState())
    val removeBasketState = _removeBasketState.asStateFlow()

    private val _updateBasketState = MutableStateFlow(UpdateBasketState())
    val updateBasketState = _updateBasketState.asStateFlow()

    private val _totalPriceState = MutableStateFlow(0.0)
    val totalPriceState: StateFlow<Double> = _totalPriceState.asStateFlow()
    init {
        getUserModel()
    }

    fun getBasket(basketRequest: GetBasketRequest) {
        viewModelScope.launch {
            getBasketUseCase.invoke(getBasketRequest = basketRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _basketState.value = BasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("basketViewmodel:Error:", "${result.msg}")
                    }

                    NetworkResult.Loading -> {
                        _basketState.value = BasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        val basketData = result.data.value
                        Log.d("API Response:", "$basketData")
                        _basketState.value = BasketState(
                            data = basketData?.distinctBy { it?.menuId },
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                        updateTotalPrice()
                        Log.d("basketViewmodel:success:", "${basketData}")

                    }
                }
            }
        }
    }


    fun getUserModel() {
        viewModelScope.launch {
            val response = getUserDatabaseUseCase.invoke()
            _userState.value = UserState(
                data = response
            )
            Log.d("getBasketRequest:", "$response")

        }
    }

    fun removeBasket(removeBasketRequest: RemoveBasketRequest) {
        viewModelScope.launch {
            removeBasketUseCase.invoke(removeBasketRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _removeBasketState.value = RemoveBasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("removeBasket:viewmodel:error:", "${result.msg}")
                    }

                    NetworkResult.Loading -> {
                        _removeBasketState.value = RemoveBasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        _removeBasketState.value = RemoveBasketState(
                            data = result.data,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                        Log.d("removeBasket:viewmodel:success:", "${result.data}")
                    }
                }

            }
        }
    }

    fun refreshBasket() {
        val getBasketRequestIns = GetBasketRequest()
        val getBasketRequest = GetBasketRequest(getBasketRequestIns.restaurantId, getBasketRequestIns.userId)
        getBasket(getBasketRequest)
    }
    fun updateQuantity(basketId: Int, newQuantity: Int) {
        viewModelScope.launch {
            val updateRequest = UpdateBasketRequest(basketId, newQuantity)
            val response = updateBasketUseCase.invoke(updateRequest)
            response.collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        Log.d("updatebasket:viewmodel:error:", "error:update")

                    }
                    NetworkResult.Loading ->{
                        Log.d("updatebasket:viewmodel:loading:", "loading:update")

                    }
                    is NetworkResult.Success -> {
                        updateTotalPrice()
                    }
                }
            }

        }
    }

    private fun updateTotalPrice() {
        _basketState.value.data?.let { basketItems ->
            _totalPriceState.value = basketItems.sumOf { basketItem ->
                val quantity = basketItem?.quantity ?: 0
                val price = basketItem?.price?.toDouble() ?: 0.0
                quantity* price
            }
        }
    }
}



data class BasketState(
    val data: List<Basket?>? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class UserState(
    val data: UserAccountModel? = null
)

data class RemoveBasketState(
    val data: RemoveBasketResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class UpdateBasketState(
    val data: UpdateBasket? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)
