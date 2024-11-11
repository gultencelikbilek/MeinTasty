package com.example.meintasty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(userAccountModel: UserAccountModel)

    @Query("SELECT * FROM useraccountmodel")
     suspend fun getToken():  UserAccountModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanton(userLocationModel: UserLocationModel)

    @Query("SELECT * FROM userlocationmodel")
    suspend fun getLocationInfo():  UserLocationModel
}