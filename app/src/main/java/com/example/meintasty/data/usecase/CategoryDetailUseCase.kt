package com.example.meintasty.data.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryDetailUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

   operator suspend fun invoke(categoryDetailRequest: CategoryDetailRequest) : Flow<NetworkResult<CategoryDetailResponse>> = flow{
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getCategoryDetail(categoryDetailRequest)
            emit(NetworkResult.Success(response))
            Log.d("CategoryDetailRepositoryImpl:response:","$response")
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}