package com.example.meintasty.domain.model.user_model_.get_order_model.get_order_response

data class OrderPage(
    val currentPage: Int?,
    val nextPage: Int?,
    val orders: List<Order?>?,
    val prevPage: Any?,
    val totalCount: Int?,
    val totalPages: Int?
)