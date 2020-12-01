package com.example.application

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.application.data.viewmodel.LoginViewModel
import com.example.application.data.viewmodel.RegisterViewModel
import com.example.application.databinding.ActivityLoginBinding
import com.example.application.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    var viewmodel: RegisterViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        val binding : ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.registerViewModel = viewmodel
        binding.lifecycleOwner = this
        observerRegister();
    }

    fun observerRegister(): Unit{
        viewmodel?.user?.observe(this,
            Observer { user ->
                if(user.token != null){
                    val settings = getSharedPreferences("APPLICATION_PREFERENCES_NAME", Context.MODE_PRIVATE)
                    settings.edit().putString("USER_TOKEN", user.token).apply()
                    startActivity(Intent(this, FeedActivity::class.java))
                }
            })
    }
}
