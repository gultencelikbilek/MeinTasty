package com.example.meintasty.domain.model.restaurant_detail

data class Order(
    val currency: String?,
    val isPaid: Boolean?,
    val orderDate: String?,
    val orderId: Int?,
    val orderPrice: String?,
    val orderTip: String?,
    val paymentType: String?,
    val restaurantId: Int?
)