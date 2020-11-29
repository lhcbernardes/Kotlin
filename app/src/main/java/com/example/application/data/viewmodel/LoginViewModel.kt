package com.example.application.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.data.LoginRepository
import com.example.application.data.model.LoginModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var userName: MutableLiveData<String> = MutableLiveData<String>();
    var password: MutableLiveData<String> = MutableLiveData<String>();
    var userEmail: MutableLiveData<String> = MutableLiveData<String>();
    var user: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var repeatpassword: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var loginRepository: LoginRepository? = null

    init{
        this.loginRepository = LoginRepository()

    }

    fun login(){
        loginRepository?.login(userEmail.value.toString(), password.value.toString())
        user = loginRepository!!.getMutableUser()
    }
}