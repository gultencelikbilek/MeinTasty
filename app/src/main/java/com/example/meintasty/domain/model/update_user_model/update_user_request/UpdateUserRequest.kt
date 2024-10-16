package com.example.meintasty.domain.model.update_user_model.update_user_request

data class UpdateUserRequest(
    val birthDate: String?,
    val email: String?,
    val fullName: String?,
    val gender: String?,
    val isEmailVerified: Boolean?,
    val isPhoneVerified: Boolean?,
    val password: String?,
    val phoneNumber: String?,
    val userId: Int?
)