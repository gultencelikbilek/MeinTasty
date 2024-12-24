package com.example.meintasty.domain.model.user_model_.signup_model.signup_response

data class SignUpResponse(
    val errorMessage: String?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: SignUp?
)