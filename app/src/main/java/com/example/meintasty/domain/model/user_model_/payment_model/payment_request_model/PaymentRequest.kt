package com.example.meintasty.domain.model.user_model_.payment_model.payment_request_model

data class PaymentRequest(
    val currencyCode: String?,
    val orderTip: String?,
    val paymentType: String?,
    val price: String?,
    val restaurantId: Int?,
    val userId: Int?
)