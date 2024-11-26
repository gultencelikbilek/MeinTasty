package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.get_order_model.get_order_request.GetOrderRequest
import com.example.meintasty.domain.model.get_order_model.get_order_response.GetOrderResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(getOrderRequest: GetOrderRequest) : Flow<NetworkResult<GetOrderResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getOrder(getOrderRequest)
            emit(NetworkResult.Success(response))
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message()))
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}