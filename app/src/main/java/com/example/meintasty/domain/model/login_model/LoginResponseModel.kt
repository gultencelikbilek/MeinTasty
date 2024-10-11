package com.example.meintasty.domain.model.login_model

import com.example.meintasty.domain.model.Value

data class LoginResponseModel(
    val errorMessage: String?,
    val infoMessage: String,
    val success: Boolean,
    val value: Value
)