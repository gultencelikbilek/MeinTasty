package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.canton_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.CantonResponseModel
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
        loginDao.loginDao().insertCanton(userLocationModel)
    }
}