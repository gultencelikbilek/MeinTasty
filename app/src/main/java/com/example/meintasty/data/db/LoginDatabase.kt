package com.example.meintasty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.meintasty.domain.model.Converters
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.Value


@Database(entities = [UserAccountModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class LoginDatabase :RoomDatabase(){
    abstract fun loginDao() : LoginDao
}