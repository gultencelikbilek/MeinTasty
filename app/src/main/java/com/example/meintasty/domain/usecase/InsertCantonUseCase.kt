package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.UserLocationModel
import javax.inject.Inject

class InsertCantonUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke(userLocationModel: UserLocationModel) {
        try {
            loginDaoRepositoryImpl.insertCanton(userLocationModel)
            Log.d("LoginRepositoryImpl", "Inserted location: ${userLocationModel}")
        } catch (e: Exception) {
            Log.e("LoginRepositoryImpl", "Insert failed: ${e.message}")
        }
    }
}