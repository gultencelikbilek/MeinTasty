package com.example.meintasty.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.CantonRequestModel
import com.example.meintasty.domain.model.CantonResponseModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CantonRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) {
    private val apiService = AppModule.providesRetrofit(context)
    suspend fun getCanton(cantonRequestModel: CantonRequestModel) :CantonResponseModel{
        return apiService.getCanton(cantonRequestModel)
    }
}