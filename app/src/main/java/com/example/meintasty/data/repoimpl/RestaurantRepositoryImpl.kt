package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.model.restaurant_model.RestaurantModelResponse
import com.example.meintasty.domain.model.restaurant_model.RestaurantRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context) {

    val apiService = AppModule.providesRetrofit(context)
    val loginDao = AppModule.providesRoom(context)

    suspend fun getRestaurant(restaurantRequest: RestaurantRequest) : RestaurantModelResponse {
        return apiService.getRestaurant(restaurantRequest)
    }

    suspend fun getLocationInfo() : UserLocationModel{
        return loginDao.loginDao().getLocationInfo()
    }

}