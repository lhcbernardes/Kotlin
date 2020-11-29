package com.example.application.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.application.data.UserRepository
import com.example.application.data.model.LoginModel

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    var userName: MutableLiveData<String> = MutableLiveData<String>();
    var password: MutableLiveData<String> = MutableLiveData<String>();
    var userEmail: MutableLiveData<String> = MutableLiveData<String>();
    var user: MutableLiveData<LoginModel> = MutableLiveData<LoginModel>();
    var repeatPassword: MutableLiveData<String> = MutableLiveData<String>();
    var userRepository: UserRepository? = null

    init{
        this.userRepository = UserRepository()

    }

    fun changeScreen(){
//        startActivity(Intent(this, MainActivity::class.java))
        }

    fun register(){
        userRepository?.register(userEmail.value.toString(), password.value.toString(), userName.value.toString())
        user = userRepository!!.getMutableUser()
    }

}