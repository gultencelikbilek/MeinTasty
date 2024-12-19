package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.remove_menu_model.remove_menu_request.RemoveMenuRequest
import com.example.meintasty.domain.model.remove_menu_model.remove_menu_response.RemoveMenuResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class RemoveMenuUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(removeMenuRequest: RemoveMenuRequest) : Flow<NetworkResult<RemoveMenuResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.removeMenu(removeMenuRequest)
            emit(NetworkResult.Success(response))
        }catch (e:HttpException){
            emit(NetworkResult.Failure(e.message()))
        }
    }
}