package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.db_model.UserLocationModel
import javax.inject.Inject

class GetLocaitonInfoUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke() : UserLocationModel {
        return loginDaoRepositoryImpl.getLocationInfo()
    }
}