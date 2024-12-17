package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.user_models.category_model.category_request.CategoryRequest
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(categoryRequest: CategoryRequest) : Flow<NetworkResult<CategoryResponse>> = flow{
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getCategoryList(categoryRequest)
            emit(NetworkResult.Success(response))
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message.toString()))
        }catch (e: IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}