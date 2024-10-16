package com.example.meintasty.domain.model.login_model.login_response

data class LoginUser(
    val userId : Int,
    val fullName: String,
    val roleList: List<String>,
    val token: String
)
