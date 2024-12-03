package com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response

data class DetailRestaurant(
    val restaurantId : Int?,
    val addressList: List<Address?>?,
    val email: String?,
    val menuList: List<Menu?>?,
    val orderList: List<Any?>?,
    val phoneNumber: String?,
    val restaurantName: String?,
    val taxNumber: String?,
    val url: String?,
    val workDayFrom: String?,
    val workDayTo: String?,
    val workHourFrom: String?,
    val workHourTo: String?
)