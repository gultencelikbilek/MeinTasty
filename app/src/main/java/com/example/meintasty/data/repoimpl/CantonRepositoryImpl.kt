package com.example.meintasty.data.repoimpl

import android.util.Log
import com.example.meintasty.data.db.LoginDatabase
import com.example.meintasty.data.network.ApiService
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.UserLocationModel
import javax.inject.Inject

class CantonRepositoryImpl @Inject constructor(
    private val apiService: ApiService, // Hilt, ApiService'i AppModule'den sağlayacak
    private val loginDatabase: LoginDatabase
) {

    suspend fun getCanton(cantonRequestModel: CantonRequestModel) : CantonResponseModel {
        return apiService.getCanton(cantonRequestModel)
    }

    suspend fun insertCanton(userLocationModel: UserLocationModel){
        try {
            loginDatabase.loginDao().insertCanton(userLocationModel)
            Log.d("CantonRepositoryImpl", "Canton inserted successfully: ${userLocationModel.cantonName}")
        } catch (e: Exception) {
            Log.e("CantonRepositoryImpl", "Error inserting canton: ${e.message}")
        }
    }
}