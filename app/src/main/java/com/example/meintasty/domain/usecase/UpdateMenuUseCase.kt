package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.domain.model.update_menu_model.update_menu_response.UpdateMenuResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateMenuUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(updateMenuRequest: UpdateMenuRequest): Flow<NetworkResult<UpdateMenuResponse>> =
        flow {
            try {
                emit(NetworkResult.Loading)
                val response = networkRepositoryImpl.updateMenu(updateMenuRequest)
                emit(NetworkResult.Success(response))
                Log.d("UpdateMenuUseCase", "$response")
            } catch (e: HttpException) {
                emit(NetworkResult.Failure(e.message()))
            } catch (e: IOException) {
                emit(NetworkResult.Failure(e.message.toString()))
            }
        }
}