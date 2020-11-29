package com.example.application.data

import android.provider.ContactsContract
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
interface LoginDataSource {

    @POST("/v1/client/auth/signin")
    fun login(email: String, password: String): Call<ResponseBody>

    @POST("/v1/client/auth/signup")
    fun register(userName: String, password: String, email: String): Call<ResponseBody>
}

