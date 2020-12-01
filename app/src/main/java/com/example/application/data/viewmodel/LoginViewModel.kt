package com.example.application.data.viewmodel

import android.app.Application
import android.content.Intent
import android.content.pm.PackageInfo
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.RegisterActivity
import com.example.application.data.UserRepository
import com.example.application.data.model.Credentials
import com.example.application.data.model.LoginModel
import com.example.application.data.retrofit.RetrofitInstance
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var registerScreen: MutableLiveData<Boolean> = MutableLiveData();
    var userName: MutableLiveData<String> = MutableLiveData<String>();

    var password: MutableLiveData<String> = MutableLiveData<String>();
    var userEmail: MutableLiveData<String> = MutableLiveData<String>();
    var user: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var repeatpassword: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var userRepository: UserRepository? = null

    init{
        this.userRepository = UserRepository()
        this.registerScreen.value = false
    }

    fun registerScreen(){
        this.registerScreen.value = true
    }

    fun login(){
        val call = RetrofitInstance().userData().login(
            Credentials(userEmail.value.toString(), password.value.toString(), null)
        );


        call.enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>?) {
                if(response!!.isSuccessful){
                    response?.body().let {
                        var objectToken: JSONObject = JSONObject(it?.string())
                        user.value = LoginModel(userEmail.value.toString(), password.value.toString(), objectToken.getString("token"))
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
    }
}