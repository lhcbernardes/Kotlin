package com.example.application.data

import android.provider.ContactsContract
import com.example.application.data.model.Credentials
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import java.io.IOException

interface NewsDataSource {

    @Headers("Content-Type: application/json")
    @GET("/v1/client/news")
    fun news(@Query("current_page") current_page: String, @Query("per_age") per_age: String,
             @Query("published_at") published_at: String, @Header ("Authorization") token: String): Call<ResponseBody>

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("/v1/client/news/highlights")
    fun highlights(): Call<ResponseBody>
}

