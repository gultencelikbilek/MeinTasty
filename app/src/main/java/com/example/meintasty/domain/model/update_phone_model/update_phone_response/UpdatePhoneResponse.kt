package com.example.meintasty.domain.model.update_phone_model.update_phone_response

data class UpdatePhoneResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Value?
)