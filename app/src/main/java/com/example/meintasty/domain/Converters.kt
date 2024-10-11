package com.example.meintasty.domain

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return if (value.isNullOrEmpty()) emptyList() else Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return Gson().toJson(list ?: emptyList<String>())
    }

}


