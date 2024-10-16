package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.domain.model.update_phone_model.update_phone_response.UpdatePhoneResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdatePhoneUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(updatePhoneRequest: UpdatePhoneRequest) : Flow<NetworkResult<UpdatePhoneResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.updatePhone(updatePhoneRequest)
            emit(NetworkResult.Success(response))
            Log.d("updateusercase:","$response")
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}