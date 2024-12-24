package com.example.meintasty.domain.usecase.user_usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.tax_model.tax_request.TaxRequest
import com.example.meintasty.domain.model.user_model_.tax_model.tax_response.TaxResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTaxUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {
    operator suspend fun invoke(taxRequest: TaxRequest) : Flow<NetworkResult<TaxResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getTax(taxRequest)
            emit(NetworkResult.Success(response))
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message()))
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}