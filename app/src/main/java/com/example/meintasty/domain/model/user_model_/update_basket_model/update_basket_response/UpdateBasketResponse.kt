package com.example.meintasty.domain.model.user_model_.update_basket_model.update_basket_response

data class UpdateBasketResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: UpdateBasket?
)