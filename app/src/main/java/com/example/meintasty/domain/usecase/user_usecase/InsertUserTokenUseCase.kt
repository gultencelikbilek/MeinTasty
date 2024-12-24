package com.example.meintasty.domain.usecase.user_usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.db_model.UserAccountModel
import javax.inject.Inject

class InsertUserTokenUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun  invoke(userAccountModel: UserAccountModel) {
        try {
           loginDaoRepositoryImpl.insertUserToken(userAccountModel)
            Log.d("LoginRepositoryImpl", "Inserted Token: ${userAccountModel.token}")
        } catch (e: Exception) {
            Log.e("LoginRepositoryImpl", "Insert failed: ${e.message}")
        }
    }
}