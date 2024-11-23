package com.example.meintasty.domain.model

import com.example.meintasty.R

data class Food(
    val id : Int,
    val img : Int,
    val name : String
)


val foodList = listOf(
    Food(
        id = 1,
        R.drawable.restaurant_bg,
        "Food 1"
    ),
    Food(
        id = 2,
        R.drawable.restaurant_bg,
        "Food 2"
    ),
    Food(
        id = 3,
        R.drawable.restaurant_bg,
        "Food 3"
    ),
    Food(
        id = 4,
        R.drawable.restaurant_bg,
        "Food 4"
    ),
    Food(
        id = 5,
        R.drawable.restaurant_bg,
        "Food 5"
    ),

)
