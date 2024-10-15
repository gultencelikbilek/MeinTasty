package com.example.meintasty.data.di

import android.content.Context
import android.util.Log
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
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(Constants.SHARED_TOKEN, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesInterceptor(sharedPreferences: android.content.SharedPreferences): Interceptor {
        return Interceptor { chain ->
            val token = sharedPreferences.getString(Constants.SHARED_TOKEN, null)
            Log.d("tokenShared", "Token: $token")

            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("accept", "*/*")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient) // provideOkHttpClient'den alınan okHttpClient kullanılıyor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRoom(@ApplicationContext context: Context): LoginDatabase {
        return Room.databaseBuilder(
            context = context,
            LoginDatabase::class.java,
            "login_db"
        ).build()
    }
}
