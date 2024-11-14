package com.example.meintasty.domain.usecase

import android.util.Log
import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.add_basket_model.db_model.AddBasketDataModel
import javax.inject.Inject

class AddBasketLocalUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke(addBasketDataModel: AddBasketDataModel) {
        try {
            loginDaoRepositoryImpl.addBasket(addBasketDataModel)
            Log.d("AddBasketLocalUseCase:success", "success")
        } catch (e: Exception) {
            Log.e("AddBasketLocalUseCase:error", "Insert failed: ${e.message}")
        }
    }
}