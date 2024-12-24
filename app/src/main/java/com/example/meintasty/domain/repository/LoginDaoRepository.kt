package com.example.meintasty.domain.repository

import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.db_model.UserLocationModel

interface LoginDaoRepository {

    suspend fun insertUserToken(userAccountModel: UserAccountModel)
    suspend fun getUserToken(): UserAccountModel
    suspend fun insertRestaurantToken(restaurantAccountModel: RestaurantAccountModel)
    suspend fun getRestaurantToken(): RestaurantAccountModel
    suspend fun insertCanton(userLocationModel: UserLocationModel)
    suspend fun getLocationInfo(): UserLocationModel
}