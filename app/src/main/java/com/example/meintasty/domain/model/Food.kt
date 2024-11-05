package com.example.meintasty.domain.model

import com.example.meintasty.R

data class Food(
    val img : Int,
    val name : String
)


val foodList = listOf(
    Food(
        R.drawable.restaurant_bg,
        "Food 1"
    ),
    Food(
        R.drawable.restaurant_bg,
        "Food 2"
    ),
    Food(
        R.drawable.restaurant_bg,
        "Food 3"
    ),
    Food(
        R.drawable.restaurant_bg,
        "Food 4"
    ),
    Food(
        R.drawable.restaurant_bg,
        "Food 5"
    ),

)
