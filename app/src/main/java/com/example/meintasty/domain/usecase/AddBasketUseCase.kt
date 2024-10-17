package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddBasketUseCase @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl
) {
    operator suspend fun invoke(addBasketRequest: AddBasketRequest): Flow<NetworkResult<AddBasketResponse>> =
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


}