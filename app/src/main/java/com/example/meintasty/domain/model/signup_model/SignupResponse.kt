package com.example.meintasty.domain.model.signup_model

data class SignupResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Signup?
)