package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.signup_model.SignupRequest
import com.example.meintasty.domain.model.signup_model.SignupResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(@ApplicationContext context: Context) {

    val apiService = AppModule.providesRetrofit(context)

    suspend fun signUp(signupRequest: SignupRequest): SignupResponse{
        return apiService.signUp(signupRequest)
    }

}