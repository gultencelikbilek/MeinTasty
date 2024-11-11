package com.example.meintasty.work_manager

import android.content.Context
import android.util.Log
import android.widget.Scroller
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.meintasty.data.Constants
import com.example.meintasty.data.network.ApiService
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.get_user_model.user_request.UserRequest
import com.example.meintasty.domain.usecase.GetUserDatabaseUseCase
import com.example.meintasty.navigation.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltWorker
class TokenRefreshWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val apiService: ApiService,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase,
   private val navController: NavController
) : CoroutineWorker(context, workerParameters) {

    val sharedPrefrences =
        context.getSharedPreferences(Constants.SHARED_TOKEN, Context.MODE_PRIVATE)


    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val userAccount = getUserDatabaseUseCase.invoke()
            Log.d("userdatabase:", "$userAccount")
            val userRequest = UserRequest(userAccount.userId)

            val newToken = apiService.getUser(userRequest)
            Log.d("newToken:", "$newToken")

            if (newToken != null) {
                val editor = sharedPrefrences.edit()
                editor.putString(Constants.SHARED_TOKEN, newToken.toString())
                editor.apply()
                navController.navigate(Screen.ChooseLoginRegisterScreen.route)
                Log.d("TokenRefreshWorker", "Yeni token alındı ve kaydedildi: $newToken")
                Result.success()
            } else {
                Log.e("TokenRefreshWorker", "Token yenileme başarısız.")
                Result.failure()
            }
        } catch (e: IOException) {
            Log.e("TokenRefreshWorker", "Network hatası: ${e.message}")
            Result.retry()
        } catch (e: HttpException) {
            Log.e("TokenRefreshWorker", "API hatası: ${e.message}")
            Result.retry()
        }
    }
}
