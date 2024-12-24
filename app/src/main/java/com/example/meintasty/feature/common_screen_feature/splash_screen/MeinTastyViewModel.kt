package com.example.meintasty.feature.common_screen_feature.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import com.example.meintasty.domain.usecase.GetLocaitonInfoUseCase
import com.example.meintasty.domain.usecase.user_usecase.GetUserDatabaseUseCase
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.db_model.UserLocationModel
import com.example.meintasty.domain.usecase.restaurant_usecase.GetRestaurantTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MeinTastyViewModel @Inject constructor(
    private val getLocaitonInfoUseCase: GetLocaitonInfoUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase,
    private val getRestaurantTokenUseCase: GetRestaurantTokenUseCase
) : ViewModel() {

    private val _splashShow = MutableStateFlow(UserTokenState())
    val splashShow = _splashShow.asStateFlow()
    private val _splashRestShow = MutableStateFlow(RestaurantTokenState())
    val splashRestShow = _splashRestShow.asStateFlow()

    private val _locaState =
        MutableStateFlow(LocationState(data = null, isNavigateLoginScreen = false))
    val locaState: StateFlow<LocationState> get() = _locaState

    init {
        runBlocking {
            getToken()
            getRestaurantToken()
            getLocation()
        }
    }

    suspend fun getLocation() {
        viewModelScope.launch {
            val locationInfo = getLocaitonInfoUseCase.invoke()
            if (locationInfo != null) {
                Log.d("splashLoc:", locationInfo.toString())
                Log.d("splashLoc:", locationInfo.cityName.toString())
                Log.d("splashLoc:", locationInfo.cantonName.toString())
                val loca = UserLocationModel(0, locationInfo.cantonName, locationInfo.cityName)
                _locaState.value = LocationState(
                    data = loca,
                    isNavigateLoginScreen = true
                )
                Log.d("locaInfo:", "$loca")
            } else {
                Log.d("splashLoc:", "locationInfo is null")
                _locaState.value = LocationState(
                    data = null,
                    isNavigateLoginScreen = false
                )
            }
        }
    }

    private suspend fun getToken() {
        val userAccount = getUserDatabaseUseCase.invoke()
        if (userAccount != null) {
            Log.d("splash:navigate:notnull", userAccount.token.toString())
            _splashShow.value = UserTokenState(
                data = userAccount,
                isNavigateLoginScreen = false,
                error = ""
            )
        } else {
            Log.d("splash:navigate:else:", "userAccount is null")
            _splashShow.value = UserTokenState(
                data = null,
                isNavigateLoginScreen = true,
                error = "User account is not found"
            )
        }
    }

    private suspend fun getRestaurantToken(){
        val restaurantAccountModel = getRestaurantTokenUseCase.invoke()
        if (restaurantAccountModel != null){
            Log.d("splash:navigate:notnull", restaurantAccountModel.token.toString())
            _splashRestShow.value = RestaurantTokenState(
                data = restaurantAccountModel,
                isNavigateLoginScreen = false,
                error = ""
            )
        }else{
            Log.d("splash:navigate:else:", "restaurantAccountModel is null")
            _splashRestShow.value = RestaurantTokenState(
                data = null,
                isNavigateLoginScreen = true,
                error = "User account is not found"
            )
        }
    }
}

data class UserTokenState(
    val data: UserAccountModel? = null,
    val isNavigateLoginScreen: Boolean? = null,
    val error: String? = ""
)
data class RestaurantTokenState(
    val data: RestaurantAccountModel? = null,
    val isNavigateLoginScreen: Boolean? = null,
    val error: String? = ""
)

data class LocationState(
    val data: UserLocationModel? = null,
    val isNavigateLoginScreen: Boolean? = null
)
