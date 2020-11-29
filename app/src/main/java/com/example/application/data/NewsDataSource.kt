package com.example.application.data

import android.provider.ContactsContract
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query
import java.io.IOException

interface NewsDataSource {

    @POST("/v1/client/news")
    fun news(@Query("current_page") current_page: String, @Query("per_age") per_age: String, @Query("published_at") published_at: String): Call<ResponseBody>

    @POST("/v1/client/news/highlights")
    fun highlights(): Call<ResponseBody>
}

