package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.update_basket_model.update_basket_request.UpdateBasketRequest
import com.example.meintasty.domain.model.update_basket_model.update_basket_response.UpdateBasketResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateBasketUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {
    operator suspend fun invoke(updateBasketRequest: UpdateBasketRequest): Flow<NetworkResult<UpdateBasketResponse>> =
        flow {
            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.updateBasket(updateBasketRequest)
                emit(NetworkResult.Success(response))
                Log.d("UpdateBasketUseCase:succes:", "$response")
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message.toString()))
            }
        }
}