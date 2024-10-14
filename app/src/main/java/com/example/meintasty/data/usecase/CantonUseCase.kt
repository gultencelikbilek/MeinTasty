package com.example.meintasty.data.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CantonUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {
    operator suspend fun invoke(cantonRequestModel: CantonRequestModel) : Flow<NetworkResult<CantonResponseModel>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getCanton(cantonRequestModel)
            emit(NetworkResult.Success(response))
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}