package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.db.LoginDatabase
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.data.network.ApiService
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.model.add_basket_model.db_model.AddBasketDataModel
import com.example.meintasty.domain.repository.LoginDaoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginDaoRepositoryImpl @Inject constructor(
    private val loginDatabase: LoginDatabase
) : LoginDaoRepository {

    override suspend fun insertToken(userAccountModel: UserAccountModel) {
        loginDatabase.loginDao().insertToken(userAccountModel)
    }

    override suspend fun getToken(): UserAccountModel {
        return loginDatabase.loginDao().getToken()
    }

    override suspend fun insertCanton(userLocationModel: UserLocationModel) {
        loginDatabase.loginDao().insertCanton(userLocationModel)
    }

    override suspend fun getLocationInfo(): UserLocationModel {
        return loginDatabase.loginDao().getLocationInfo()
    }

    override suspend fun addBasket(addBasketDataModel: AddBasketDataModel) {
        return loginDatabase.loginDao().addBasket(addBasketDataModel)
    }

    override suspend fun allBasket(): List<AddBasketDataModel> {
        return loginDatabase.loginDao().allBasket()
    }

    override suspend fun updateQuantity(menuId: Int, newQuantity: Int) {
        return loginDatabase.loginDao().updateQuantity(menuId, newQuantity)
    }
}