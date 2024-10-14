package com.example.meintasty.data.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.UserLocationModel
import javax.inject.Inject

class GetLocaitonInfoUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke() : UserLocationModel{
        return loginDaoRepositoryImpl.getLocationInfo()
    }
}