package com.example.meintasty.domain.model.signup_model.signup_request

data class SignupRequest(
    val email: String?,
    val fullName: String?,
    val password: String?,
    val phoneNumber: String?,
    val rePassword: String?
)