package com.example.meintasty.domain.model.user_model_.signup_model.signup_response

data class SignUp(
    val fullName: String?,
    val roleList: List<String?>?,
    val token: String?,
    val userId: Int?
)