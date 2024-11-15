package com.example.meintasty.domain.repository

import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel

interface LoginDaoRepository {

    suspend fun insertToken(userAccountModel: UserAccountModel)
    suspend fun getToken(): UserAccountModel
    suspend fun insertCanton(userLocationModel: UserLocationModel)
    suspend fun getLocationInfo(): UserLocationModel
}