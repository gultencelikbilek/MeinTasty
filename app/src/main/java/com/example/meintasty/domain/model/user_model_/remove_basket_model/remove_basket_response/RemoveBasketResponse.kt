package com.example.meintasty.domain.model.user_model_.remove_basket_model.remove_basket_response

data class RemoveBasketResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Value?
)