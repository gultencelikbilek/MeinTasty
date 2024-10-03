package com.example.meintasty.data.di

import android.content.Context
import androidx.room.Room
import com.example.meintasty.data.Constants
import com.example.meintasty.data.db.LoginDatabase
import com.example.meintasty.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val interceptor = Interceptor { chain ->
        val originRequest = chain.request()
        val newRequest = originRequest.newBuilder()
            .addHeader("accept","*/*")
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(@ApplicationContext context: Context): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRoom(@ApplicationContext context: Context) : LoginDatabase {
        return Room.databaseBuilder(
            context = context,
            LoginDatabase::class.java,
            "login_db"
        ).build()
    }
}