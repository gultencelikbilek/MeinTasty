package com.example.meintasty.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.UserAccountModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(@ApplicationContext context : Context) {
    private val roomDb = AppModule.providesRoom(context).loginDao()

    suspend fun getToken() : UserAccountModel{
        return roomDb.getToken()
    }
}