package com.example.meintasty.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.login_model.LoginUserRequest
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.login_model.LoginResponseModel
import com.example.meintasty.domain.model.login_model.LoginUser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(@ApplicationContext context : Context) {


    val api = AppModule.providesRetrofit(context)
    val loginDao = AppModule.providesRoom(context)

    suspend fun login(loginUserRequest: LoginUserRequest) : LoginResponseModel {
        return api.loginUser(loginUserRequest)
    }

    suspend fun insertToken(userAccountModel: UserAccountModel){
        try {
            loginDao.loginDao().insertToken(userAccountModel)
            Log.d("LoginRepositoryImpl", "Inserted Token: ${userAccountModel.token}")
        } catch (e: Exception) {
            Log.e("LoginRepositoryImpl", "Insert failed: ${e.message}")
        }
    }
}