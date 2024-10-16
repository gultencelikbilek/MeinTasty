package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.model.get_user_model.user_response.UserResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(userRequest: UserRequest) : Flow<NetworkResult<UserResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getUser(userRequest)
            emit(NetworkResult.Success(response))
            Log.d("getusercase:","$response")
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
            Log.d("getusercase:","${e.message}")
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.message.toString()))
            Log.d("getusercase:","${e.message}")
        }
    }
}