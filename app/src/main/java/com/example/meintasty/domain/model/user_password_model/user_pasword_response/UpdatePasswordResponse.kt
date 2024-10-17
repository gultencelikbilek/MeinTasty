package com.example.meintasty.domain.model.user_password_model.user_pasword_response

data class UpdatePasswordResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Value?
)