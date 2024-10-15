package com.example.meintasty.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.meintasty.data.db.LoginDatabase
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.data.network.ApiService
import com.example.meintasty.domain.model.UserAccountModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(
    private val loginDatabase: LoginDatabase
) {
    private val roomDb = loginDatabase.loginDao()

    suspend fun getToken() : UserAccountModel{
        return roomDb.getToken()
    }
}