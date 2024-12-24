package com.example.meintasty.domain.usecase.restaurant_usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import javax.inject.Inject

class InsertRestaurantTokenUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl ) {

    suspend operator fun invoke(restaurantAccountModel: RestaurantAccountModel) {
        try {
            loginDaoRepositoryImpl.insertRestaurantToken(restaurantAccountModel)
            Log.d("InsertRestaurantTokenUseCase", "Insert successful: ${restaurantAccountModel.token}")
        } catch (e: Exception) {
            Log.e("InsertRestaurantTokenUseCase", "Insert failed: ${e.message}")
        }
    }
}
