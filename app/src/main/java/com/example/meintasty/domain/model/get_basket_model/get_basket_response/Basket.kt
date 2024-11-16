package com.example.meintasty.domain.model.get_basket_model.get_basket_response

data class Basket(
    val basketDate: String?,
    val currencyCode: String?,
    val id: Int?,
    val menuId: Int?,
    val menuName: String?,
    val price: String?,
    var quantity: Int?,
    val restaurantId: Int?,
    val restaurantName: String?,
    val userId: Int?
)