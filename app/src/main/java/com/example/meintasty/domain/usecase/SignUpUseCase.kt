package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignUpResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(signupRequest: SignupRequest) : Flow<NetworkResult<SignUpResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val result = networkRepositoryImpl.signUp(signupRequest)
            emit(NetworkResult.Success(result))
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}