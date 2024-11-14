package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.LoginDaoRepositoryImpl
import javax.inject.Inject

class UpdateBasketQuantityUseCase @Inject constructor(private val loginDaoRepositoryImpl: LoginDaoRepositoryImpl) {

    operator suspend fun invoke(menuId: Int, newQuantity: Int) {
        loginDaoRepositoryImpl.updateQuantity(menuId, newQuantity)
    }
}