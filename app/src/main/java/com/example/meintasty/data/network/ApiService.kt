package com.example.meintasty.data.network

import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.create_menu_model.create_menu_request.CreateMenuRequest
import com.example.meintasty.domain.model.create_menu_model.create_menu_response.CreateMenuResponse
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurantResponse
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.GetBasketResponse
import com.example.meintasty.domain.model.get_order_model.get_order_request.GetOrderRequest
import com.example.meintasty.domain.model.get_order_model.get_order_response.GetOrderResponse
import com.example.meintasty.domain.model.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.model.get_user_model.user_response.UserResponse
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.login_model.login_response.LoginResponseModel
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.payment_model.payment_request_model.PaymentRequest
import com.example.meintasty.domain.model.payment_model.payment_response_model.PaymentResponse
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.domain.model.remove_menu_model.remove_menu_request.RemoveMenuRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_request.RestaurantLoginRequest
import com.example.meintasty.domain.model.restaurant_login_model.restaurant_login_response.RestaurantLoginResponse
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignUpResponse
import com.example.meintasty.domain.model.update_basket_model.update_basket_request.UpdateBasketRequest
import com.example.meintasty.domain.model.update_basket_model.update_basket_response.UpdateBasketResponse
import com.example.meintasty.domain.model.update_email_model.update_email_request.EmailUpdateRequest
import com.example.meintasty.domain.model.update_email_model.update_email_response.EmailUpdateResponse
import com.example.meintasty.domain.model.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.domain.model.update_menu_model.update_menu_response.UpdateMenuResponse
import com.example.meintasty.domain.model.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.domain.model.update_phone_model.update_phone_response.UpdatePhoneResponse
import com.example.meintasty.domain.model.user_models.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.user_password_model.user_pasword_request.UpdatePasswordRequest
import com.example.meintasty.domain.model.user_password_model.user_pasword_response.UpdatePasswordResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.END_POINT_USER_LOGIN)
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest): LoginResponseModel

    @POST(Constants.END_POINT_RESTAURANT_LOGIN)
    suspend fun restaurantLogin(@Body restaurantLoginRequest: RestaurantLoginRequest): RestaurantLoginResponse

    @POST(Constants.END_POINT_SIGNUP)
    suspend fun signUp(@Body signupRequest: SignupRequest): SignUpResponse

    @POST(Constants.ENDPOINT_CANTON)
    suspend fun getCanton(@Body cantonRequestModel: CantonRequestModel): CantonResponseModel

    @POST(Constants.END_POINT_RESTAURANT)
    suspend fun getRestaurant(@Body restaurantRequest: RestaurantRequest): RestaurantModelResponse

    @POST(Constants.END_POINT_DETAIL_RESTAURANT)
    suspend fun getDetailRestaurant(@Body detailRestaurantRequest: DetailRestaurantRequest): RestaurantDetailResponse

    @POST(Constants.END_POINT_CATEGORY)
    suspend fun getCategoryList(@Body categoryRequest: CategoryRequest): CategoryResponse

    @POST(Constants.END_POINT_CATEGORY_DETAIL)
    suspend fun getCategoryDetail(@Body categoryDetailRequest: CategoryDetailRequest): CategoryDetailResponse

    @POST(Constants.END_POINT_USER)
    suspend fun getUser(@Body userRequest: UserRequest): UserResponse

    @POST(Constants.ENT_POINT_UPDATE_PHONE)
    suspend fun updatePhone(@Body updatePhoneRequest: UpdatePhoneRequest): UpdatePhoneResponse

    @POST(Constants.ENT_POINT_UPDATE_EMAIL)
    suspend fun updateEmail(@Body emailUpdateRequest: EmailUpdateRequest): EmailUpdateResponse

    @POST(Constants.ENT_POINT_UPDATE_PASSWORD)
    suspend fun updatePassword(@Body updatePasswordRequest: UpdatePasswordRequest): UpdatePasswordResponse

    @POST(Constants.END_POINT_ADD_BASKET)
    suspend fun addBasket(@Body addBasketRequest: AddBasketRequest): AddBasketResponse

    @POST(Constants.END_POINT_GET_BASKET)
    suspend fun getBasket(@Body getBasketRequest: GetBasketRequest): GetBasketResponse

    @POST(Constants.END_POINT_REMOVE_BASKET)
    suspend fun removeBasket(@Body removeBasketRequest: RemoveBasketRequest): RemoveBasketResponse

    @POST(Constants.END_POINT_FAVORITE_RESTAURANT)
    suspend fun getFavoritesRestaurant(@Body favoriteRestaurantRequest: FavoritesRestaurantRequest): FavoriteRestaurantResponse

    @POST(Constants.END_POINT_UPDATE_BASKET)
    suspend fun updateBasket(@Body updateBasket: UpdateBasketRequest): UpdateBasketResponse

    @POST(Constants.END_POINT_GET_ORDER)
    suspend fun getOrder(@Body getOrderRequest: GetOrderRequest): GetOrderResponse

    @POST(Constants.END_POINT_ADD_ORDER)
    suspend fun addOrder(@Body paymentRequest: PaymentRequest): PaymentResponse

    @POST(Constants.END_POINT_CREATE_MENU)
    suspend fun createMenu(@Body createMenuRequest: CreateMenuRequest) : CreateMenuResponse

    @POST(Constants.END_POINT_UPDATE_MENU)
    suspend fun updateMenu(@Body updateMenuRequest: UpdateMenuRequest) : UpdateMenuResponse

   /* @POST(Constants.END_POINT_REMOVE_MENU)
    suspend fun removeMenu(@Body removeMenuRequest: RemoveMenuRequest) : RemoveMenuResponse*/

}