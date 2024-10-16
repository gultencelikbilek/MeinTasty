package com.example.meintasty.domain.model.get_user_model.user_response

data class UserResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: User?
)