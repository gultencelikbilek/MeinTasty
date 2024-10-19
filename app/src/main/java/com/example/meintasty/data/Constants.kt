package com.example.meintasty.data

object Constants {
    const val BASE_URL = "http://185.210.95.239:72/api/"
    const val END_POINT_LOGIN = "Login/generateToken"
    const val ENDPOINT_CANTON = "Canton/getCantonsAndCities"
    const val END_POINT_RESTAURANT = "Restaurant/getRestaurantsByCityId"
    const val END_POINT_DETAIL_RESTAURANT = "Restaurant/getRestaurantDetailById"
    const val END_POINT_CATEGORY = "Category/getCategories"
    const val END_POINT_CATEGORY_DETAIL = "Category/getRestaurantsByCategoryId"
    const val END_POINT_SIGNUP = "Register/signup"
    const val END_POINT_USER = "User/getUser"
    const val ENT_POINT_UPDATE_PHONE ="User/updateUserPhone"
    const val ENT_POINT_UPDATE_EMAIL ="User/updateUserEmail"
    const val ENT_POINT_UPDATE_PASSWORD ="User/updateUserPassword"
    const val END_POINT_ADD_BASKET = "Basket/addBasket"
    const val END_POINT_GET_BASKET = "Basket/getBaskets"

    const val SHARED_PREF = "city_code"
    const val SHARED_TOKEN = "token_code"
    const val SHARED_RESTAURANT_ID = "restaurant_id"
}