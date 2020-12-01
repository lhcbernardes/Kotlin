package com.example.application.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.data.UserRepository
import com.example.application.data.model.LoginModel
import com.example.application.data.model.RegisterModel
import com.example.application.data.retrofit.RetrofitInstance
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    var loginScreen: MutableLiveData<Boolean> = MutableLiveData();
    var userName: MutableLiveData<String> = MutableLiveData<String>();
    var password: MutableLiveData<String> = MutableLiveData<String>();
    var userEmail: MutableLiveData<String> = MutableLiveData<String>();
    var user: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var repeatPassword: MutableLiveData<String> = MutableLiveData<String>();
    var userRepository: UserRepository? = null

    init{
        this.userRepository = UserRepository()
        this.loginScreen.value = false

    }

    fun loginScreen(){
        this.loginScreen.value = true
    }

    fun register(){
        val call = RetrofitInstance().userData().register(
            RegisterModel(userEmail.value.toString(), password.value.toString(), userName.value.toString())
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
                Log.e("onFailure error", t?.message)
                user.value = null
            }
        })
    }

}