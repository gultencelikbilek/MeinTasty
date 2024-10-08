package com.example.meintasty.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAccountModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = 0,
    val fullName: String? ="",
    val roleList : List<String> = emptyList(),
    val token: String? =""
)
