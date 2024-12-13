package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import javax.inject.Inject

class GetUserDatabaseUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke(): UserAccountModel {
        return loginDaoRepositoryImpl.getUserToken()
    }
}