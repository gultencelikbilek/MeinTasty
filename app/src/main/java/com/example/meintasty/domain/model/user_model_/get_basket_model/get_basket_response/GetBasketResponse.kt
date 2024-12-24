package com.example.meintasty.domain.model.user_model_.get_basket_model.get_basket_response

data class GetBasketResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: List<Basket?>?
)