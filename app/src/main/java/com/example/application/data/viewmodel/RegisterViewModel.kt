package com.example.application.data.viewmodel

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.MainActivity
import com.example.application.data.LoginRepository
import com.example.application.data.model.LoginModel

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    var userName: MutableLiveData<String> = MutableLiveData<String>();
    var password: MutableLiveData<String> = MutableLiveData<String>();
    var userEmail: MutableLiveData<String> = MutableLiveData<String>();
    var user: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var repeatPassword: MutableLiveData<String> = MutableLiveData<String>();
    var loginRepository: LoginRepository? = null

    init{
        this.loginRepository = LoginRepository()

    }

    fun changeScreen(){
//        startActivity(Intent(this, MainActivity::class.java))
        }

    fun register(){
        loginRepository?.login(userEmail.value.toString(), password.value.toString())
        user = loginRepository!!.getMutableUser()
    }

}