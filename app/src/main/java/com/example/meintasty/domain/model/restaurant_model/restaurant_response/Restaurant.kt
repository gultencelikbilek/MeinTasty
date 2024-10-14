package com.example.meintasty.domain.model.restaurant_model.restaurant_response

data class Restaurant(
    val email: String? = "",
    val id: Int? = 0,
    val phoneNumber: String? = "",
    val restaurantName: String? = "",
    val taxNumber: Any? = null,
    val workDayFrom: String?= "",
    val workDayTo: String? = "",
    val workHourFrom: String? = "",
    val workHourTo: String? =""
)