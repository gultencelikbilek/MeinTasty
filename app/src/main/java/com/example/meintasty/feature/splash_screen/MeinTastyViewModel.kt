package com.example.meintasty.feature.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.usecase.GetLocaitonInfoUseCase
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MeinTastyViewModel @Inject constructor(
    private val getLocaitonInfoUseCase: GetLocaitonInfoUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase
) : ViewModel() {

    private val _splashShow = MutableStateFlow(TokenState())
    val splashShow = _splashShow.asStateFlow()
    private val _locaState = MutableStateFlow(LocationState())
    val locaState = _locaState.asStateFlow()

    init {
        runBlocking {
            getToken()
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
            } else {
                Log.d("splashLoc:", "locationInfo is null")
                _locaState.value = LocationState(
                    data = null,
                    isNavigateLoginScreen = true
                )
            }
        }
    }

    private suspend fun getToken() {
        val userAccount = getUserDatabaseUseCase.invoke()
        if (userAccount != null) {
            Log.d("splash:navigate:notnull", userAccount.token.toString())
            _splashShow.value = TokenState(
                data = userAccount,
                isNavigateLoginScreen = false,
                error = ""
            )
        } else {
            Log.d("splash:navigate:else:", "userAccount is null")
            _splashShow.value = TokenState(
                data = null,
                isNavigateLoginScreen = true,
                error = "User account is not found"
            )
        }
    }
}

    data class TokenState(
    val data: UserAccountModel? = null,
    val isNavigateLoginScreen: Boolean? = null,
    val error: String? = ""
)

data class LocationState(
    val data: UserLocationModel? = null,
    val isNavigateLoginScreen: Boolean? = null
)
