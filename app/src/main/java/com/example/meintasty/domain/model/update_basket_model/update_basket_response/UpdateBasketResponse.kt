package com.example.meintasty.domain.model.update_basket_model.update_basket_response

data class UpdateBasketResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: UpdateBasket?
)