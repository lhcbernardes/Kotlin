package com.example.application.data

import android.provider.ContactsContract
import com.example.application.data.model.Credentials
import com.example.application.data.model.RegisterModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
interface UserDataSource {

    @POST("/v1/client/auth/signin")
    fun login(@Body credenciais: Credentials): Call<ResponseBody>

    @Headers("Content-Type: application/json; charset=utf-8")
    @POST("/v1/client/auth/signup")
    fun register(@Body credenciais: RegisterModel): Call<ResponseBody>
}

