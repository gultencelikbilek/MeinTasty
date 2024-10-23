package com.example.meintasty.domain.model.signup_model.signup_response

data class SignUpResponse(
    val errorMessage: String?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: SignUp?
)