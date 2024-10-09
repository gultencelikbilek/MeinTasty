package com.example.meintasty.feature.restaurant_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.RestaurantRepositoryImpl
import com.example.meintasty.domain.model.Restaurant
import com.example.meintasty.domain.model.RestaurantRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepoImpl : RestaurantRepositoryImpl
) : ViewModel(){

    private val _restaurantState = MutableStateFlow(RestaurantState())
    val restaurantState = _restaurantState.asStateFlow()

    suspend fun getRestaurant(restaurantRequest: RestaurantRequest){
        viewModelScope.launch {
          val response =  restaurantRepoImpl.getRestaurant(restaurantRequest)
            _restaurantState.value = RestaurantState(
                data = response.value,
                error = ""
            )
            Log.d("restaurantviewmodel:",response.value.toString())
        }
    }
}

data class RestaurantState(
    val data : List<Restaurant>? = null,
    val error : String? = ""
)