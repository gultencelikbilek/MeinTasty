package com.example.meintasty.domain.model.login_model

data class LoginResponseModel(
    val errorMessage: String?,
    val infoMessage: String,
    val success: Boolean,
    val value: LoginUser
)