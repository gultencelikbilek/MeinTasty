package com.example.meintasty.feature.restaurant_feature.update_remove_menu_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.restaurant_model_.remove_menu_model.remove_menu_request.RemoveMenuRequest
import com.example.meintasty.domain.model.restaurant_model_.remove_menu_model.remove_menu_response.RemoveMenuResponse
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_response.DetailRestaurant
import com.example.meintasty.domain.model.restaurant_model_.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.domain.model.restaurant_model_.update_menu_model.update_menu_response.UpdateMenuResponse
import com.example.meintasty.domain.usecase.restaurant_usecase.RemoveMenuUseCase
import com.example.meintasty.domain.usecase.user_usecase.RestaurantDetailUseCase
import com.example.meintasty.domain.usecase.restaurant_usecase.UpdateMenuUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateRemoveMenuViewModel @Inject constructor(
    private val updateMenuUseCase: UpdateMenuUseCase,
    private val restaurantDetailUseCase: RestaurantDetailUseCase,
    private val removeMenuUseCase: RemoveMenuUseCase
):ViewModel() {
    private val _updateMneuState = MutableStateFlow(UpdateMenuState())
    val updateMenuState = _updateMneuState.asStateFlow()

    private val _restaurantMenuUpdateState = MutableStateFlow(RestaurantUpdateMenuState())
    val restaurantMenuUpdateState = _restaurantMenuUpdateState.asStateFlow()

    private val _removeMenuState = MutableStateFlow(RemoveMenuState())
    val removeMenuState = _removeMenuState.asStateFlow()
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
                        Log.d("UpdateRemoveMenuViewModel:updateMenu:error","${result.msg}")
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
                        Log.d("UpdateRemoveMenuViewModel:updateMenu:succes","${result.data}")

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
                            Log.d("UpdateRemoveMenuViewModel:detail:error:", result.msg)
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
                                "UpdateRemoveMenuViewModel:detail:succes:",
                                result.data.value.toString()
                            )
                        }
                    }
                }
        }
    }

    fun removeMenu(removeMenuRequest: RemoveMenuRequest){
        viewModelScope.launch {
            removeMenuUseCase.invoke(removeMenuRequest).collect{result->
                when(result){
                    is NetworkResult.Failure -> {
                        _removeMenuState.value = RemoveMenuState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("UpdateRemoveMenuViewModel:removeMenu:error:", result.msg)
                    }
                    NetworkResult.Loading ->{
                        _removeMenuState.value = RemoveMenuState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _removeMenuState.value = RemoveMenuState(
                            data = result.data,
                            isSuccess = true,
                            isLoading = false,
                            isError = ""
                        )
                        Log.d("UpdateRemoveMenuViewModel:removeMenu:error:", "$result.data")
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
data class RemoveMenuState(
    val data: RemoveMenuResponse? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)