package com.example.meintasty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.meintasty.domain.Converters
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel


@Database(entities = [UserAccountModel::class,UserLocationModel::class], version = 2)
@TypeConverters(Converters::class)
abstract class LoginDatabase :RoomDatabase(){
    abstract fun loginDao() : LoginDao
}