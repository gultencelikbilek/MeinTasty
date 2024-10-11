package com.example.meintasty.feature.restaurant_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.RestaurantRepositoryImpl
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.model.restaurant_model.Restaurant
import com.example.meintasty.domain.model.restaurant_model.RestaurantRequest
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

    private val _locationState = MutableStateFlow(LocationState())
    val locationState = _locationState.asStateFlow()
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

    fun getLocationInfo(){
        viewModelScope.launch {
            val responseLocaiton = restaurantRepoImpl.getLocationInfo()
            _locationState.value = LocationState(
                data = responseLocaiton
            )
        }
    }
}

data class RestaurantState(
    val data : List<Restaurant>? = null,
    val error : String? = ""
)

data class LocationState(
    val data : UserLocationModel? = null
)