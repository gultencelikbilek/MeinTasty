package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.update_email_model.update_email_request.EmailUpdateRequest
import com.example.meintasty.domain.model.update_email_model.update_email_response.EmailUpdateResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EmailUpdateUseCase @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl
) {

    operator suspend fun invoke(emailUpdateRequest: EmailUpdateRequest) : Flow<NetworkResult<EmailUpdateResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.updateEmail(emailUpdateRequest)
            emit(NetworkResult.Success(response))
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
            Log.d("emailUpdateUseCase:","${e.message}")
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
            Log.d("emailUpdateUseCase:","${e.message}")
        }
    }
}