package com.example.application.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.application.data.model.Credentials
import com.example.application.data.model.LoginModel
import com.example.application.data.model.RegisterModel
import com.example.application.data.retrofit.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class UserRepository() {

    // in-memory cache of the loggedInUser object
    var user: MutableLiveData<LoginModel> = MutableLiveData()

    fun logout() {
    }

    fun login(email: String, password: String) {
        val call = RetrofitInstance().userData().login(
            Credentials(email, password, null));


        call.enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                response?.body().let {
                    user.value = LoginModel(email, password, it.toString())
                    println(it.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                // Implement onFailure here
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun register(email: String, password: String, name: String) {
        val call = RetrofitInstance().userData().register(
            RegisterModel(email, password, name)
        );


        call.enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                response?.body().let {
                    user.value = LoginModel(email, password, it.toString())
                    println(it.toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                // Implement onFailure here
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun getMutableUser(): MutableLiveData<LoginModel> {
        return user
    }

}
