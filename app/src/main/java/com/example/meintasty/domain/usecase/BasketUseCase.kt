package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.GetBasketResponse
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl
) {
    suspend fun addBasketUseCase(addBasketRequest: AddBasketRequest): Flow<NetworkResult<AddBasketResponse>> =
        flow {
            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.addBasket(addBasketRequest)
                emit(NetworkResult.Success(response))
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message.toString()))
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            }
        }

    suspend fun getBasketUseCase(getBasketRequest: GetBasketRequest): Flow<NetworkResult<GetBasketResponse>> =
        flow {
            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.getBasket(getBasketRequest)
                emit(NetworkResult.Success(response))
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message.toString()))
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            }
        }

    suspend fun removeBasket(removeBasketRequest: RemoveBasketRequest) : Flow<NetworkResult<RemoveBasketResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.removeBasket(removeBasketRequest)
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Failure(e.message.toString()))
        } catch (e: IOException) {
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}