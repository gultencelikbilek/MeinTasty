package com.example.meintasty.feature.order_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.get_order_model.get_order_request.GetOrderRequest
import com.example.meintasty.domain.model.get_order_model.get_order_response.GetOrderResponse
import com.example.meintasty.domain.model.get_order_model.get_order_response.OrderPage
import com.example.meintasty.domain.usecase.GetOrderUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.feature.NetworkResult
import com.example.meintasty.feature.profile_screen.UserDatabaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getOrderUseCase: GetOrderUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase,
    )  :ViewModel(){

    private val _userDatabaseState = MutableStateFlow(UserDatabaseState())
    val userDatabaseState = _userDatabaseState.asStateFlow()

    private val _getOrderState = MutableStateFlow(GetOrderState())
    val getOrderState = _getOrderState.asStateFlow()
    fun getOrder(getOrderRequest: GetOrderRequest){
        viewModelScope.launch {
            getOrderUseCase.invoke(getOrderRequest).collect{result->
                when(result){
                    is NetworkResult.Failure -> {
                        _getOrderState.value = GetOrderState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("profileViewmodel:error:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _getOrderState.value = GetOrderState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _getOrderState.value = GetOrderState(
                            data = result.data,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                        Log.d("profileViewmodel:success:","${result.data.value}")
                        Log.d("profileViewmodel:success:","${result.data}")

                    }
                }
            }
        }
    }

    fun getUserDatabaseModel() {
        viewModelScope.launch {
            val response = getUserDatabaseUseCase.invoke()
            Log.d("userdatabase:",  "$response")
            response.userId?.let {
                Log.d("userrequest:userId1:",  "${it}")
                if (it != null && it > 0) {
                    Log.d("userrequest:userId2::",  "${it}")
                    _userDatabaseState.value = UserDatabaseState(
                        data = response
                    )
                }
            }
        }
    }


}
data class GetOrderState(
    val data : GetOrderResponse? = null,
    val orderPage : OrderPage? = null,
    val isSucces : Boolean? = false,
    val isLoading: Boolean? = false,
    val isError : String? = ""
)
data class UserDatabaseState(
    val data: UserAccountModel? = null
)
