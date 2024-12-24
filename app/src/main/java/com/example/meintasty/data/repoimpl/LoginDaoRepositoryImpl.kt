package com.example.meintasty.data.repoimpl

import com.example.meintasty.data.db.LoginDatabase
import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.db_model.UserLocationModel
import com.example.meintasty.domain.repository.LoginDaoRepository
import javax.inject.Inject

class LoginDaoRepositoryImpl @Inject constructor(
    private val loginDatabase: LoginDatabase
) : LoginDaoRepository {

    override suspend fun insertUserToken(userAccountModel: UserAccountModel) {
        loginDatabase.loginDao().insertUserToken(userAccountModel)
    }

    override suspend fun getUserToken(): UserAccountModel {
        return loginDatabase.loginDao().getUserToken()
    }

    override suspend fun insertRestaurantToken(restaurantAccountModel: RestaurantAccountModel) {
        return loginDatabase.loginDao().insertRestaurantToken(restaurantAccountModel)
    }

    override suspend fun getRestaurantToken(): RestaurantAccountModel {
        return loginDatabase.loginDao().getRestaurantToken()
    }

    override suspend fun insertCanton(userLocationModel: UserLocationModel) {
        loginDatabase.loginDao().insertCanton(userLocationModel)
    }

    override suspend fun getLocationInfo(): UserLocationModel {
        return loginDatabase.loginDao().getLocationInfo()
    }

}