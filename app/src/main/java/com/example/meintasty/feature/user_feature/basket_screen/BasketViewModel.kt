package com.example.meintasty.feature.user_feature.basket_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.model.tax_model.tax_request.TaxRequest
import com.example.meintasty.domain.model.tax_model.tax_response.Tax
import com.example.meintasty.domain.model.tax_model.tax_response.TaxResponse
import com.example.meintasty.domain.model.update_basket_model.update_basket_request.UpdateBasketRequest
import com.example.meintasty.domain.model.update_basket_model.update_basket_response.UpdateBasket
import com.example.meintasty.domain.usecase.AddBasketUseCase
import com.example.meintasty.domain.usecase.GetBasketUseCase
import com.example.meintasty.domain.usecase.GetTaxUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.domain.usecase.RemoveBasketUseCase
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.usecase.UpdateBasketUseCase
import com.example.meintasty.feature.NetworkResult
import com.example.meintasty.feature.user_feature.detail_restaurant.AddBasketState
import com.example.meintasty.feature.user_feature.detail_restaurant.DetailRestaurantState
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
    private val addBasketUseCase : AddBasketUseCase,
    private val getTaxUseCase: GetTaxUseCase
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

    private val _addBasketState = MutableStateFlow(AddBasketScreenState())
    val addBasketState = _addBasketState.asStateFlow()

    private val _getTaxState= MutableStateFlow(GetTaxState())
    val getTaxState = _getTaxState.asStateFlow()

    private var taxAmount: Double = 0.0

    init {
        getUserModel()
        viewModelScope.launch {
            _basketState.collect {
                updateTotalPrice()
            }
        }
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
                            data = basketData?.distinctBy { it?.id },
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
                        val basketRemove = result.data
                        _removeBasketState.value = RemoveBasketState(
                            data = basketRemove,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                        refreshBasket()
                        Log.d("removeBasket:viewmodel:success:", "${result.data}")
                    }
                }
            }
        }
    }

    fun refreshBasket() {
        val getBasketRequestIns = GetBasketRequest()
        val getBasketRequest =
            GetBasketRequest(getBasketRequestIns.restaurantId, getBasketRequestIns.userId)
        getBasket(getBasketRequest)
    }

    fun addBasket(addBasketRequest: AddBasketRequest){
        viewModelScope.launch {
            addBasketUseCase.invoke(addBasketRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure ->{
                        _addBasketState.value = AddBasketScreenState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("basketViewModel:addBasket:error","${result.msg}")
                    }
                    NetworkResult.Loading ->{
                        _addBasketState.value = AddBasketScreenState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _addBasketState.value = AddBasketScreenState(
                            data = result.data,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                        refreshBasket()
                        Log.d("basketViewModel:addBasket:succes","${result.data}")

                    }
                }
            }
        }
    }

    // UpdateTotalPrice fonksiyonu
    private fun updateTotalPrice() {
        _basketState.value.data?.let { basketItems ->
            _totalPriceState.value = basketItems.sumOf { basketItem ->
                val quantity = basketItem?.quantity ?: 0
                val price = basketItem?.price?.replace(",", ".")?.trim()?.toDoubleOrNull() ?: 0.0
                Log.d("quantity:price:","$quantity $price")
                1 * price
            } + taxAmount // taxAmount değeri ekleniyor
        }
    }

    fun getTax() {
        viewModelScope.launch {
            getTaxUseCase.invoke(TaxRequest()).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _getTaxState.value = GetTaxState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("basketViewModel:error:getTax:", "${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _getTaxState.value = GetTaxState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _getTaxState.value = GetTaxState(
                            data = result.data,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )

                        val taxList: List<Tax?> = result.data.value ?: emptyList()
                        taxAmount = taxList.sumOf { tax ->
                            tax?.amount?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
                        }

                        // Yeni total price'ı hesapla
                        updateTotalPrice()
                        Log.d("basketViewModel:error:getTax:", "${result.data}")
                    }
                }
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

data class AddBasketScreenState(
    val data: AddBasketResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class GetTaxState(
    val data :TaxResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)