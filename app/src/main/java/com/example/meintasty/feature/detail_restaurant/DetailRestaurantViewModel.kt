package com.example.meintasty.feature.detail_restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.DetailRestaurantRespositoryImpl
import com.example.meintasty.domain.model.restaurant_detail.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.DetailRestaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(
    private val detailRestaurantRespositoryImpl: DetailRestaurantRespositoryImpl
) :ViewModel(){

    private val _detailRestState= MutableStateFlow(DetailRestaurantState())
    val detailRestState = _detailRestState.asStateFlow()

     fun  getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest){
         viewModelScope.launch {
             val response = detailRestaurantRespositoryImpl.getDetailRestaurant(detailRestaurantRequest)
             _detailRestState.value = DetailRestaurantState(
                 data = response.value
             )
         }
     }



}

data class DetailRestaurantState(
    val data : DetailRestaurant? = null
)