package com.example.meintasty.domain.model.user_model_.login_model.login_response

data class LoginResponseModel(
    val errorMessage: String?,
    val infoMessage: String,
    val success: Boolean,
    val value: LoginUser
)