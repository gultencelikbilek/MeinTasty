package com.example.meintasty.domain.model.login_model

data class LoginUser(
    val fullName: String,
    val roleList: List<String>,
    val token: String
)
