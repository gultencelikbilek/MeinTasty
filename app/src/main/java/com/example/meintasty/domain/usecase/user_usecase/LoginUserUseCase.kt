package com.example.meintasty.domain.usecase.user_usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.user_model_.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.user_model_.login_model.login_response.LoginResponseModel
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {
    operator suspend fun invoke(loginUserRequest: LoginUserRequest) : Flow<NetworkResult<LoginResponseModel>>  = flow{
        try {
            emit(NetworkResult.Loading)
            val response =  networkRepositoryImpl.loginUser(loginUserRequest)
            emit(NetworkResult.Success(response))
        }catch (e:Exception){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}