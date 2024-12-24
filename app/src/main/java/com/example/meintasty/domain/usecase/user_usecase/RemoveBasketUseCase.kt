package com.example.meintasty.domain.usecase.user_usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.user_model_.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoveBasketUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(removeBasketRequest: RemoveBasketRequest): Flow<NetworkResult<RemoveBasketResponse>> =
        flow {
            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.removeBasket(removeBasketRequest)
                emit(NetworkResult.Success(response))
                Log.d("removeBasketRequest:", "$response")
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message()))
            }
        }
}