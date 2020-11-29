package com.example.application

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.application.data.viewmodel.LoginViewModel
import com.example.application.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    var viewmodel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.loginViewModel = viewmodel
        binding.lifecycleOwner = this
    }

    fun observerLogin(): Unit{
        viewmodel?.user?.observe(this,
            Observer { user ->
                if(user.token != null){
                    val settings = getSharedPreferences("APPLICATION_PREFERENCES_NAME", Context.MODE_PRIVATE)
                    settings.edit().putString("USER_TOKEN", user.token).apply()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            })
    }
}
