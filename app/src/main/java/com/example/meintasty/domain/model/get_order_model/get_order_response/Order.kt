package com.example.meintasty.domain.model.get_order_model.get_order_response

data class Order(
    val currencyCode: String?,
    val id: Int?,
    val name: String?,
    val orderDate: String?,
    val orderTip: String?,
    val paymentType: String?,
    val price: String?,
    val restaurantId: Int?,
    val userId: Int?
)