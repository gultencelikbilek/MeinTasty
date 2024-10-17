package com.example.meintasty.domain.model.user_password_model.user_pasword_request

data class UpdatePasswordRequest(
    val password: String?,
    val userId: Int?
)