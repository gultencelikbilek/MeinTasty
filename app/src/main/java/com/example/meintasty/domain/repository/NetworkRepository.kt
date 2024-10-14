package com.example.meintasty.domain.repository

import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.domain.model.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.login_model.login_response.LoginResponseModel
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignupResponse

interface NetworkRepository {

    suspend fun loginUser(loginUserRequest: LoginUserRequest): LoginResponseModel
    suspend fun getCanton(cantonRequestModel: CantonRequestModel): CantonResponseModel
    suspend fun signUp(signupRequest: SignupRequest): SignupResponse
    suspend fun getRestaurant(restaurantRequest: RestaurantRequest): RestaurantModelResponse
    suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest): RestaurantDetailResponse
    suspend fun getCategoryList(categoryRequest: CategoryRequest): CategoryResponse
    suspend fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest): CategoryDetailResponse

}