package com.example.meintasty.data.usecase

import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {
    operator suspend fun invoke() : UserAccountModel{
        return loginDaoRepositoryImpl.getToken()
    }
}