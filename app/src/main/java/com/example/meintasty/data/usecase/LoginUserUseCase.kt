package com.example.meintasty.data.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.login_model.login_response.LoginResponseModel
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(loginUserRequest: LoginUserRequest) : Flow<NetworkResult<LoginResponseModel>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.loginUser(loginUserRequest)
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Failure(e.message.toString()))
        } catch (e: IOException) {
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}