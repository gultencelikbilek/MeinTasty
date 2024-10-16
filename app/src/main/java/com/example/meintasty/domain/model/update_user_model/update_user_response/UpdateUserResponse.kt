package com.example.meintasty.domain.model.update_user_model.update_user_response

data class UpdateUserResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Value?
)