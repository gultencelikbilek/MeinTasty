package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import javax.inject.Inject

class InsertTokenUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun  invoke(userAccountModel: UserAccountModel) {
        try {
           loginDaoRepositoryImpl.insertToken(userAccountModel)
            Log.d("LoginRepositoryImpl", "Inserted Token: ${userAccountModel.token}")
        } catch (e: Exception) {
            Log.e("LoginRepositoryImpl", "Insert failed: ${e.message}")
        }
    }
}