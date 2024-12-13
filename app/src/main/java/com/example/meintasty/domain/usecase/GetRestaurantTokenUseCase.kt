package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import com.example.meintasty.domain.model.RestaurantAccountModel
import javax.inject.Inject

class GetRestaurantTokenUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke() : RestaurantAccountModel{
        return loginDaoRepositoryImpl.getRestaurantToken()
    }
}