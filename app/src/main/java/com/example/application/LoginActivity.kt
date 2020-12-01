package com.example.application

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.application.data.viewmodel.LoginViewModel
import com.example.application.databinding.ActivityLoginBinding
import com.facebook.FacebookSdk
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {
    var viewmodel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(getApplicationContext());
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.loginViewModel = viewmodel
        binding.lifecycleOwner = this

        observe()
    }
    private fun printKeyHash(){
        try {
            val info = packageManager.getPackageInfo("com.example.application", PackageManager.GET_SIGNATURES)
            for(signature in info.signatures){
                val md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray())
                Log.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))

            }
        }
        catch (e:PackageManager.NameNotFoundException){

        }
        catch (e:NoSuchAlgorithmException){

        }
        }



    fun observerLogin(): Unit{
        viewmodel?.user?.observe(this,
            Observer { user ->
                if(user.token != null && user.token!!.length > 0){
                    val settings = getSharedPreferences("APPLICATION_PREFERENCES_NAME", Context.MODE_PRIVATE)
                    settings.edit().putString("USER_TOKEN", user.token).apply()
                    startActivity(Intent(this, FeedActivity::class.java))
                }
            })
    }
    fun observe(){
        observerRegister();
        observerLogin();
    }
    fun observerRegister(){
        viewmodel?.registerScreen?.observe(this,
            Observer { registerScreen ->
                if(registerScreen){
                    startActivity(Intent(this, RegisterActivity::class.java))
                }
            })
    }
}
