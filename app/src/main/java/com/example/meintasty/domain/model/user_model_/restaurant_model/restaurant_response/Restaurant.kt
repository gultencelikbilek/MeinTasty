package com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_response

data class Restaurant(
    val email: String?,
    val id: Int?,
    val phoneNumber: String?,
    val restaurantName: String?,
    val taxNumber: Any?,
    val totalCount: Int?,
    val url: String?,
    val workDayFrom: String?,
    val workDayTo: String?,
    val workHourFrom: String?,
    val workHourTo: String?
)