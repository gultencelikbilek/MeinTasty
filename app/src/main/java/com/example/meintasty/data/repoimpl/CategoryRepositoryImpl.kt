package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.category_model.CategoryRequest
import com.example.meintasty.domain.model.category_model.CategoryResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(@ApplicationContext context: Context) {

    val apiService = AppModule.providesRetrofit(context)

    suspend fun getCategoriesList(categoryRequest: CategoryRequest) : CategoryResponse{
        return apiService.getCategoryList(categoryRequest)
    }
}