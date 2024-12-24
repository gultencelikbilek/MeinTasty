package com.example.meintasty.domain.model.user_model_.add_basket_model.add_basket_response

data class AddBasketResponse(
    val errorMessage: String?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: AddBasket?
)