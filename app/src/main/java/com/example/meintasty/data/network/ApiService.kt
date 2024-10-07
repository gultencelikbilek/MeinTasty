package com.example.meintasty.data.network

import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.CantonRequestModel
import com.example.meintasty.domain.model.CantonResponseModel
import com.example.meintasty.domain.model.LoginResponseModel
import com.example.meintasty.domain.model.LoginUserRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.END_POINT_LOGIN)
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest) : LoginResponseModel

    @POST(Constants.ENPOINT_CANTON)
    suspend fun getCanton(@Body cantonRequestModel: CantonRequestModel) : CantonResponseModel
}