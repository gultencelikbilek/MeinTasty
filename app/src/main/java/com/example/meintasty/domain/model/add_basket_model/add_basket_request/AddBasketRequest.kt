package com.example.meintasty.domain.model.add_basket_model.add_basket_request

data class AddBasketRequest(
    val basketDate: String?,
    val currencyCode: String?,
    val menuId: Int?,
    val price: String?,
    val quantity: Int?,
    val restaurantId: Int?,
    val userId: Int?
)