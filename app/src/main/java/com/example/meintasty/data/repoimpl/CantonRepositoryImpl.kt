package com.example.meintasty.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.UserLocationModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CantonRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) {
    private val apiService = AppModule.providesRetrofit(context)

    private val loginDao = AppModule.providesRoom(context)
    suspend fun getCanton(cantonRequestModel: CantonRequestModel) : CantonResponseModel {
        return apiService.getCanton(cantonRequestModel)
    }

    suspend fun insertCanton(userLocationModel: UserLocationModel){
        try {
            loginDao.loginDao().insertCanton(userLocationModel)
            Log.d("CantonRepositoryImpl", "Canton inserted successfully: ${userLocationModel.cantonName}")
        } catch (e: Exception) {
            Log.e("CantonRepositoryImpl", "Error inserting canton: ${e.message}")
        }
    }

}