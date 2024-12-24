package com.example.meintasty.domain.usecase.user_usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.user_password_model.user_pasword_request.UpdatePasswordRequest
import com.example.meintasty.domain.model.user_model_.user_password_model.user_pasword_response.UpdatePasswordResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdatePasswordUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(updatePasswordRequest: UpdatePasswordRequest) : Flow<NetworkResult<UpdatePasswordResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.updatePassword(updatePasswordRequest)
            emit(NetworkResult.Success(response))
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}