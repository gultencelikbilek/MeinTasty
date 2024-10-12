package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.category_detail_model.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.CategoryDetailResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategoryDetailRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context) {

    val apiService = AppModule.providesRetrofit(context)

    suspend fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest) : CategoryDetailResponse{
        return  apiService.getCategoryDetail(categoryDetailRequest)
    }
}