package com.example.meintasty.domain.usecase.user_usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.payment_model.payment_request_model.PaymentRequest
import com.example.meintasty.domain.model.user_model_.payment_model.payment_response_model.PaymentResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddOrderUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(paymentRequest: PaymentRequest) : Flow<NetworkResult<PaymentResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.addOrder(paymentRequest)
            emit(NetworkResult.Success(response))
            Log.d("addOrderUseCase:","$response")
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message()))
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}