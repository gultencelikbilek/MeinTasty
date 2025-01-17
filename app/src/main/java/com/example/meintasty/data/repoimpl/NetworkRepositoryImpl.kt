package com.example.meintasty.data.repoimpl


import com.example.meintasty.data.network.ApiService
import com.example.meintasty.domain.model.user_model_.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.user_model_.add_basket_model.add_basket_response.AddBasketResponse
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.user_model_.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.user_model_.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.restaurant_model_.create_menu_model.create_menu_request.CreateMenuRequest
import com.example.meintasty.domain.model.restaurant_model_.create_menu_model.create_menu_response.CreateMenuResponse
import com.example.meintasty.domain.model.user_model_.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.user_model_.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurantResponse
import com.example.meintasty.domain.model.user_model_.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.user_model_.get_basket_model.get_basket_response.GetBasketResponse
import com.example.meintasty.domain.model.user_model_.get_order_model.get_order_request.GetOrderRequest
import com.example.meintasty.domain.model.user_model_.get_order_model.get_order_response.GetOrderResponse
import com.example.meintasty.domain.model.user_model_.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.model.user_model_.get_user_model.user_response.UserResponse
import com.example.meintasty.domain.model.user_model_.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.user_model_.login_model.login_response.LoginResponseModel
import com.example.meintasty.domain.model.user_model_.payment_model.payment_request_model.PaymentRequest
import com.example.meintasty.domain.model.user_model_.payment_model.payment_response_model.PaymentResponse
import com.example.meintasty.domain.model.user_model_.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.user_model_.remove_basket_model.remove_basket_response.RemoveBasketResponse
import com.example.meintasty.domain.model.restaurant_model_.remove_menu_model.remove_menu_request.RemoveMenuRequest
import com.example.meintasty.domain.model.restaurant_model_.remove_menu_model.remove_menu_response.RemoveMenuResponse
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.domain.model.restaurant_model_.restaurant_login_model.restaurant_login_request.RestaurantLoginRequest
import com.example.meintasty.domain.model.restaurant_model_.restaurant_login_model.restaurant_login_response.RestaurantLoginResponse
import com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.domain.model.user_model_.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.user_model_.signup_model.signup_response.SignUpResponse
import com.example.meintasty.domain.model.user_model_.tax_model.tax_request.TaxRequest
import com.example.meintasty.domain.model.user_model_.tax_model.tax_response.TaxResponse
import com.example.meintasty.domain.model.user_model_.update_basket_model.update_basket_request.UpdateBasketRequest
import com.example.meintasty.domain.model.user_model_.update_basket_model.update_basket_response.UpdateBasketResponse
import com.example.meintasty.domain.model.user_model_.update_email_model.update_email_request.EmailUpdateRequest
import com.example.meintasty.domain.model.user_model_.update_email_model.update_email_response.EmailUpdateResponse
import com.example.meintasty.domain.model.restaurant_model_.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.domain.model.restaurant_model_.update_menu_model.update_menu_response.UpdateMenuResponse
import com.example.meintasty.domain.model.user_model_.update_phone_model.update_phone_request.UpdatePhoneRequest
import com.example.meintasty.domain.model.user_model_.update_phone_model.update_phone_response.UpdatePhoneResponse
import com.example.meintasty.domain.model.user_models.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.user_model_.user_password_model.user_pasword_request.UpdatePasswordRequest
import com.example.meintasty.domain.model.user_model_.user_password_model.user_pasword_response.UpdatePasswordResponse
import com.example.meintasty.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NetworkRepository {

    override suspend fun loginUser(loginUserRequest: LoginUserRequest): LoginResponseModel {
        return apiService.loginUser(loginUserRequest)
    }

    override suspend fun getCanton(cantonRequestModel: CantonRequestModel): CantonResponseModel {
        return apiService.getCanton(cantonRequestModel)
    }

    override suspend fun signUp(signupRequest: SignupRequest): SignUpResponse {
        return apiService.signUp(signupRequest)
    }

    override suspend fun getRestaurant(restaurantRequest: RestaurantRequest): RestaurantModelResponse {
        return apiService.getRestaurant(restaurantRequest)
    }

    override suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest): RestaurantDetailResponse {
        return apiService.getDetailRestaurant(detailRestaurantRequest)
    }

    override suspend fun getCategoryList(categoryRequest: CategoryRequest): CategoryResponse {
        return apiService.getCategoryList(categoryRequest)
    }

    override suspend fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest): CategoryDetailResponse {
        return apiService.getCategoryDetail(categoryDetailRequest)
    }

    override suspend fun getUser(userRequest: UserRequest): UserResponse {
        return apiService.getUser(userRequest)
    }

    override suspend fun updatePhone(updatePhoneRequest: UpdatePhoneRequest): UpdatePhoneResponse {
        return apiService.updatePhone(updatePhoneRequest)
    }

    override suspend fun updateEmail(emailUpdateRequest: EmailUpdateRequest): EmailUpdateResponse {
        return apiService.updateEmail(emailUpdateRequest)
    }

    override suspend fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): UpdatePasswordResponse {
        return apiService.updatePassword(updatePasswordRequest)
    }

    override suspend fun addBasket(addBasketRequest: AddBasketRequest): AddBasketResponse {
        return apiService.addBasket(addBasketRequest)
    }

    override suspend fun getBasket(getBasketRequest: GetBasketRequest): GetBasketResponse {
        return apiService.getBasket(getBasketRequest)
    }

    override suspend fun removeBasket(removeBasketRequest: RemoveBasketRequest): RemoveBasketResponse {
        return apiService.removeBasket(removeBasketRequest)
    }

    override suspend fun getFavoritesRestaurant(favoriteRestaurantRequest: FavoritesRestaurantRequest): FavoriteRestaurantResponse {
        return apiService.getFavoritesRestaurant(favoriteRestaurantRequest)
    }

    override suspend fun updateBasket(updateBasket: UpdateBasketRequest): UpdateBasketResponse {
        return apiService.updateBasket(updateBasket)
    }

    override suspend fun getOrder(getOrderRequest: GetOrderRequest): GetOrderResponse {
        return apiService.getOrder(getOrderRequest)
    }

    override suspend fun addOrder(paymentRequest: PaymentRequest): PaymentResponse {
        return apiService.addOrder(paymentRequest)
    }

    override suspend fun restaurantLogin(restaurantLoginRequest: RestaurantLoginRequest): RestaurantLoginResponse {
        return apiService.restaurantLogin(restaurantLoginRequest)
    }

    override suspend fun createMenu(createMenuRequest: CreateMenuRequest): CreateMenuResponse {
        return apiService.createMenu(createMenuRequest)
    }

    override suspend fun updateMenu(updateMenuRequest: UpdateMenuRequest): UpdateMenuResponse {
        return apiService.updateMenu(updateMenuRequest)
    }

    override suspend fun removeMenu(removeMenuRequest: RemoveMenuRequest): RemoveMenuResponse {
        return apiService.removeMenu(removeMenuRequest)
    }

    override suspend fun getTax(taxRequest: TaxRequest): TaxResponse {
        return apiService.getTax(taxRequest)
    }
}