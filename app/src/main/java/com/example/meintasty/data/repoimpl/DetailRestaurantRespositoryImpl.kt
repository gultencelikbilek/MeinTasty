package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.DetailRestaurantRequest
import com.example.meintasty.domain.model.DetailRestaurantResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DetailRestaurantRespositoryImpl @Inject constructor(@ApplicationContext context: Context){

    private val apiService = AppModule.providesRetrofit(context)

    suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) : DetailRestaurantResponse{
        return apiService.getDetailRestaurant(detailRestaurantRequest)
    }
}