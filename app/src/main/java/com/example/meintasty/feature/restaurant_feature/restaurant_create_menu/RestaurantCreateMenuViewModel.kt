package com.example.meintasty.feature.restaurant_feature.restaurant_create_menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.create_menu_model.create_menu_request.CreateMenuRequest
import com.example.meintasty.domain.model.create_menu_model.create_menu_response.CreateMenuResponse
import com.example.meintasty.domain.usecase.CreateMenuUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantCreateMenuViewModel @Inject constructor(
    private val createMenuUseCase: CreateMenuUseCase) :
    ViewModel() {

    private val _createMenuState = MutableStateFlow(CreateMenuState())
    val createMenuState = _createMenuState.asStateFlow()

    fun createMenu(createMenuRequest: CreateMenuRequest){
        viewModelScope.launch {
            createMenuUseCase.invoke(createMenuRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _createMenuState.value = CreateMenuState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("RestaurantCreateMenuViewModel:error:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _createMenuState.value = CreateMenuState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _createMenuState.value = CreateMenuState(
                            data = result.data,
                            isSucces = true,
                            isLoading = false,
                            isError =""
                        )
                        Log.d("RestaurantCreateMenuViewModel:success:","${result.data}")
                    }
                }
            }
        }
    }
}

data class CreateMenuState(
    val data : CreateMenuResponse? = null,
    val isSucces : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError : String? = ""
)