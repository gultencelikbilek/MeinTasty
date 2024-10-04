package com.example.meintasty.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.LoginUserRequest
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.Value
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(@ApplicationContext context : Context) {

    val api = AppModule.providesRetrofit(context)

    val loginDao = AppModule.providesRoom(context)
    suspend fun getToken(email : String, password : String) : Value {
        val loginResquest = LoginUserRequest(email,password)
        val response = api.loginUser(loginResquest)
        Log.d("token",response.toString())
        Log.d("token",response.success.toString())
        return response.value
    }

    suspend fun insertToken(userAccountModel: UserAccountModel) =  flow<Unit>{
        loginDao.loginDao().insertToken(userAccountModel)
        Log.d("loginRepositoryImpl:",userAccountModel.token.toString())
    }
}