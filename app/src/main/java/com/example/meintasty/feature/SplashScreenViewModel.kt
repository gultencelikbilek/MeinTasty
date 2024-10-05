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
    private val repositoryImpl: SplashScreenRepositoryImpl
) : ViewModel(){


    private val _tokenExists = MutableLiveData<Boolean>()
    val tokenExists: LiveData<Boolean> get() = _tokenExists
     fun checkToken(token:String) {
        viewModelScope.launch {
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