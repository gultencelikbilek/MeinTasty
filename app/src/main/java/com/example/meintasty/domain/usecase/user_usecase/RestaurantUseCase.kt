package com.example.meintasty.domain.usecase.user_usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {
    operator suspend fun invoke(restaurantRequest: RestaurantRequest) : Flow<NetworkResult<RestaurantModelResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getRestaurant(restaurantRequest)
            emit(NetworkResult.Success(response))
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}