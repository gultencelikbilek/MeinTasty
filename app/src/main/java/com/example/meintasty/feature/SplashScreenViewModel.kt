package com.example.meintasty.feature

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.SplashScreenRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repositoryImpl: SplashScreenRepositoryImpl,
    private val context: Context
) : ViewModel(){

    private val _tokenExists = MutableLiveData<Boolean>()
    val tokenExists: LiveData<Boolean> get() = _tokenExists
    init {
        checkToken()
    }
    private fun checkToken() {
        viewModelScope.launch {

            val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("auth_token", null) // Eğer token yoksa null döner


            if (token != null) {
                val userAccount = repositoryImpl.getToken(token)
                Log.d("token","$token")
                _tokenExists.value = userAccount != null
            } else {
                _tokenExists.value = false
            }
        }
    }

}