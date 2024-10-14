package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.repository.LoginDaoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginDaoRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    LoginDaoRepository {

    private val loginDao = AppModule.providesRoom(context).loginDao()
    override suspend fun insertToken(userAccountModel: UserAccountModel) {
        loginDao.insertToken(userAccountModel)
    }

    override suspend fun getToken(): UserAccountModel {
        return loginDao.getToken()
    }

    override suspend fun insertCanton(userLocationModel: UserLocationModel) {
        loginDao.insertCanton(userLocationModel)
    }

    override suspend fun getLocationInfo(): UserLocationModel {
        return loginDao.getLocationInfo()
    }
}