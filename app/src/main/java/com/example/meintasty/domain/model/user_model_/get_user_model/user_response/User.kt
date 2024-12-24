package com.example.meintasty.domain.model.user_model_.get_user_model.user_response

data class User(
    val birthDate: String?,
    val email: String?,
    val fullName: String?,
    val gender: String?,
    val id: Int?,
    val isEmailVerified: Boolean?,
    val isPhoneVerified: Boolean?,
    val phoneNumber: String?,
    val profilePicture: String?,
    val userAdddress: UserAdddress?
)