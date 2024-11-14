package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.add_basket_model.db_model.AddBasketDataModel
import javax.inject.Inject

class AllBasketUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {
    operator suspend fun invoke(): List<AddBasketDataModel> {
        return loginDaoRepositoryImpl.allBasket()
    }
}