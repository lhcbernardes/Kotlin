package com.example.application.data.retrofit

import com.example.application.data.UserDataSource
import com.example.application.data.NewsDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    // API
    val path = "https://mesa-news-api.herokuapp.com"
    var retrofitInstance: Retrofit

    init {

        // LogInterceptor
        val logging = HttpLoggingInterceptor()
        val logEnabled = true

        // Log level
        logging.level = if (logEnabled) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        // Debbug
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            //.addInterceptor(logging)
            .build()

        retrofitInstance = Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // Call services
    public fun userData() = retrofitInstance.create(UserDataSource::class.java)
    public fun newsData() = retrofitInstance.create(NewsDataSource::class.java)

}