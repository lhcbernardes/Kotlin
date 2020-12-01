package com.example.application.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.application.data.model.Credentials
import com.example.application.data.model.LoginModel
import com.example.application.data.model.RegisterModel
import com.example.application.data.retrofit.RetrofitInstance
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class UserRepository() {

    fun logout() {
    }

    fun login(email: String, password: String): MutableLiveData<LoginModel> {
        var user:  MutableLiveData<LoginModel> =  MutableLiveData<LoginModel>();
        val call = RetrofitInstance().userData().login(
            Credentials(email, password, null));


        call.enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                if(response!!.isSuccessful){
                    response?.body().let {
                        var objectToken: JSONObject = JSONObject(it?.string())
                            user.value = LoginModel(email, password, objectToken.getString("token"))
                            println(it.toString())
                    }
                }
                else{
                    response.errorBody().let {
                        println(it.toString())
                    }
                }


            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                // Implement onFailure here
                user.value = null
                Log.e("onFailure error", t?.message)
            }
        })
        println(user.value?.token)
        return user
    }

    fun register(email: String, password: String, name: String): MutableLiveData<LoginModel> {
        var user:  MutableLiveData<LoginModel> =  MutableLiveData<LoginModel>();
        val call = RetrofitInstance().userData().register(
            RegisterModel(email, password, name)
        );


        call.enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                if(response!!.isSuccessful){
                    response?.body().let {
                        var objectToken: JSONObject = JSONObject(it?.string())
                        user.value = LoginModel(email, password, objectToken.getString("token"))
                        println(it.toString())
                    }
                }
                else{
                    response.errorBody().let {
                        println(it.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                // Implement onFailure here
                Log.e("onFailure error", t?.message)
                user.value = null
            }
        })
        return user
    }


}
