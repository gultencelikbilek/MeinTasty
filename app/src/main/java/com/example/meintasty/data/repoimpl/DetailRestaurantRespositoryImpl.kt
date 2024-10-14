package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.restaurant_detail.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.RestaurantDetailResponse
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailRestaurantRespositoryImpl @Inject constructor(@ApplicationContext context: Context) {

    private val apiService = AppModule.providesRetrofit(context)


    suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest) : RestaurantDetailResponse{
        return apiService.getDetailRestaurant(detailRestaurantRequest)
    }
   // suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest): Flow<NetworkResult<RestaurantDetailResponse>> =
   //     flow {
   //         try {
   //             val response = apiService.getDetailRestaurant(detailRestaurantRequest)
   //             emit(response)
   //         } catch (e: HttpException) {
   //             emit(NetworkResult.Failure(e.message.toString()))
   //         } catch (e: IOException) {
   //             emit(NetworkResult.Failure(e.message.toString()))
   //         }
//
   //     }
}