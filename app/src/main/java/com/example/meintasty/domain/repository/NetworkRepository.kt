package com.example.meintasty.domain.repository

import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.domain.model.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.GetBasketResponse
import com.example.meintasty.domain.model.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.model.get_user_model.user_response.UserResponse
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.login_model.login_response.LoginResponseModel
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignUpResponse
import com.example.meintasty.domain.model.update_email_model.update_email_request.EmailUpdateRequest
import com.example.meintasty.domain.model.update_email_model.update_email_response.EmailUpdateResponse
import com.example.meintasty.domain.model.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.domain.model.update_phone_model.update_phone_response.UpdatePhoneResponse
import com.example.meintasty.domain.model.user_password_model.user_pasword_request.UpdatePasswordRequest
import com.example.meintasty.domain.model.user_password_model.user_pasword_response.UpdatePasswordResponse
import retrofit2.http.Body

interface NetworkRepository {

    suspend fun loginUser(loginUserRequest: LoginUserRequest): LoginResponseModel
    suspend fun getCanton(cantonRequestModel: CantonRequestModel): CantonResponseModel
    suspend fun signUp(signupRequest: SignupRequest): SignUpResponse
    suspend fun getRestaurant(restaurantRequest: RestaurantRequest): RestaurantModelResponse
    suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest): RestaurantDetailResponse
    suspend fun getCategoryList(categoryRequest: CategoryRequest): CategoryResponse
    suspend fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest): CategoryDetailResponse
    suspend fun getUser(userRequest: UserRequest): UserResponse
    suspend fun updatePhone(updatePhoneRequest: UpdatePhoneRequest): UpdatePhoneResponse
    suspend fun updateEmail(emailUpdateRequest: EmailUpdateRequest): EmailUpdateResponse
    suspend fun updatePassword(updatePasswordRequest: UpdatePasswordRequest) : UpdatePasswordResponse
    suspend fun addBasket(addBasketRequest: AddBasketRequest) : AddBasketResponse
    suspend fun getBasket(getBasketRequest: GetBasketRequest) : GetBasketResponse
    suspend fun removeBasket(removeBasketRequest: RemoveBasketRequest) : RemoveBasketResponse

}