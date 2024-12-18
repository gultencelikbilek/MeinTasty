package com.example.meintasty.feature.restaurant_feature.update_remove_menu_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.model.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.domain.model.update_menu_model.update_menu_response.UpdateMenuResponse
import com.example.meintasty.domain.usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.usecase.UpdateMenuUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateRemoveMenuViewModel @Inject constructor(
    private val updateMenuUseCase: UpdateMenuUseCase,
    private val restaurantDetailUseCase: RestaurantDetailUseCase
):ViewModel() {
    private val _updateMneuState = MutableStateFlow(UpdateMenuState())
    val updateMenuState = _updateMneuState.asStateFlow()
    private val _restaurantMenuUpdateState = MutableStateFlow(RestaurantUpdateMenuState())
    val restaurantMenuUpdateState = _restaurantMenuUpdateState.asStateFlow()

    fun updateMenu(updateMenuRequest: UpdateMenuRequest){
        viewModelScope.launch {
            updateMenuUseCase.invoke(updateMenuRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure ->{
                        _updateMneuState.value = UpdateMenuState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("UpdateRemoveMenuViewModel:error","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _updateMneuState.value = UpdateMenuState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _updateMneuState.value = UpdateMenuState(
                            data = result.data,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                        Log.d("UpdateRemoveMenuViewModel:succes","${result.data}")

                    }
                }
            }
        }
    }
    fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) {
        viewModelScope.launch {
            restaurantDetailUseCase.invoke(detailRestaurantRequest)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Failure -> {
                            _restaurantMenuUpdateState.value = RestaurantUpdateMenuState(
                                data = null,
                                isSuccess = false,
                                isLoading = false,
                                isError = result.msg
                            )
                            Log.d("restaurantProfileViewmodel:error:", result.msg)
                        }

                        NetworkResult.Loading -> {
                            _restaurantMenuUpdateState.value = RestaurantUpdateMenuState(
                                data = null,
                                isSuccess = true,
                                isLoading = false,
                                isError = null
                            )
                        }

                        is NetworkResult.Success -> {
                            _restaurantMenuUpdateState.value = RestaurantUpdateMenuState(
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

data class UpdateMenuState(
    val data : UpdateMenuResponse? = null,
    val isSuccess : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError  : String? = ""
)
data class RestaurantUpdateMenuState(
    val data: DetailRestaurant? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)