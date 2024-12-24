package com.example.meintasty.domain.usecase.restaurant_usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.restaurant_model_.restaurant_login_model.restaurant_login_request.RestaurantLoginRequest
import com.example.meintasty.domain.model.restaurant_model_.restaurant_login_model.restaurant_login_response.RestaurantLoginResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantLoginUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(restaurantLoginRequest: RestaurantLoginRequest) : Flow<NetworkResult<RestaurantLoginResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.restaurantLogin(restaurantLoginRequest)
            emit(NetworkResult.Success(response))
            Log.d("restaurantLoginUseCase:", "$response")
        } catch (e: HttpException) {
            emit(NetworkResult.Failure(e.message.toString()))
        } catch (e: IOException) {
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}