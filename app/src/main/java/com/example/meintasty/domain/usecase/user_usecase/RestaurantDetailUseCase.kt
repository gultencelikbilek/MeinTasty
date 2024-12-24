package com.example.meintasty.domain.usecase.user_usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantDetailUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator   suspend fun invoke(detailRestaurantRequest: DetailRestaurantRequest): Flow<NetworkResult<RestaurantDetailResponse>> =
        flow {
            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.getDetailRestaurant(detailRestaurantRequest)
                emit(NetworkResult.Success(response))
                Log.d("RestaurantDetailUseCase:succes:", "${response.value}")
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message.toString()))
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            }

        }
}