package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.GetBasketResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBasketUseCase @Inject constructor(private val repositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(getBasketRequest: GetBasketRequest) : Flow<NetworkResult<GetBasketResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = repositoryImpl.getBasket(getBasketRequest)
            emit(NetworkResult.Success(response))
            Log.d("getBasketUseCase","$response")
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message().toString()))
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.toString()))
        }
    }
}