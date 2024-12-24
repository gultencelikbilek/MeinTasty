package com.example.meintasty.domain.model.user_model_.add_basket_model.add_basket_request

data class AddBasketRequest(
    val currencyCode: String?,
    val menuId: Int?,
    val price: String?,
    val quantity: Int?,
    val restaurantId: Int?,
    val isReplaceBasket : Boolean? = false

)