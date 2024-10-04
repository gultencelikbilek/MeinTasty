package com.example.meintasty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meintasty.domain.model.UserAccountModel

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(userAccountModel: UserAccountModel)

    @Query("SELECT * FROM useraccountmodel WHERE token = :token LIMIT 1")
    suspend fun getToken(token: String): UserAccountModel?


}