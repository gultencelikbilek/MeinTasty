package com.example.meintasty.domain.model.payment_model.payment_response_model

data class PaymentResponse(
    val errorMessage: String?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Payment?
)