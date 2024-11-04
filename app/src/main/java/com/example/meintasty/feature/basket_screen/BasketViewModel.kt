package com.example.meintasty.feature.basket_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.GetBasketResponse
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.domain.usecase.GetBasketUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketUseCase: GetBasketUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase
) : ViewModel() {

    private val _basketState = MutableStateFlow(BasketState())
    val basketState = _basketState.asStateFlow()

    private val _userState = MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()
    init {
        getUserModel()
    }
    fun getBasket(basketRequest: GetBasketRequest){
        viewModelScope.launch {

            getBasketUseCase.invoke(getBasketRequest = basketRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _basketState.value = BasketState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("basketViewmodelError:","${result.msg}")
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
            Log.d("getBasketRequest:","$response")

        }
    }

}

data class BasketState(
    val data : List<Basket?>? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class UserState(
    val data : UserAccountModel? = null
)

data class RemoveBasketState(
    val data : RemoveBasketResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)