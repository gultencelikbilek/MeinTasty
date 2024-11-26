package com.example.meintasty.domain.model.get_order_model.get_order_response

data class GetOrderResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: OrderPage?
)