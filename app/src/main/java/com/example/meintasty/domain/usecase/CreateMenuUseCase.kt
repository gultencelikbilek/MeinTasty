package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.create_menu_model.create_menu_request.CreateMenuRequest
import com.example.meintasty.domain.model.create_menu_model.create_menu_response.CreateMenuResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreateMenuUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {
    operator suspend fun invoke(createMenuRequest: CreateMenuRequest): Flow<NetworkResult<CreateMenuResponse>> =
        flow {

            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.createMenu(createMenuRequest)
                emit(NetworkResult.Success(response))
                Log.d("CreateMenuUseCase:", "$response")
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message()))
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            }
        }
}