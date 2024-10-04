package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(@ApplicationContext context : Context) {
    private val roomDb = AppModule.providesRoom(context).loginDao()

    suspend fun getToken(token:String){
        roomDb.getToken(token)
    }

}