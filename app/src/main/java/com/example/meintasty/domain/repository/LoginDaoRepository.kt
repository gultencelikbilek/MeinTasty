package com.example.meintasty.domain.repository

import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.model.add_basket_model.db_model.AddBasketDataModel

interface LoginDaoRepository {

    suspend fun insertToken(userAccountModel: UserAccountModel)
    suspend fun getToken(): UserAccountModel
    suspend fun insertCanton(userLocationModel: UserLocationModel)
    suspend fun getLocationInfo(): UserLocationModel
    suspend fun addBasket(addBasketDataModel: AddBasketDataModel)
    suspend fun allBasket() : List<AddBasketDataModel>
    suspend fun updateQuantity(menuId: Int, newQuantity: Int)
}