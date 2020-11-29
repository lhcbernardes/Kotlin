package com.example.application.data.viewmodel

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.RegisterActivity
import com.example.application.data.UserRepository
import com.example.application.data.model.LoginModel

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
        userRepository?.login(userEmail.value.toString(), password.value.toString())
        user = userRepository!!.getMutableUser()
    }
}