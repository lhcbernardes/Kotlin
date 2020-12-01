package com.example.application

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(""+ getSharedPreferences("APPLICATION_PREFERENCES_NAME", Context.MODE_PRIVATE).getString("USER_TOKEN", "SEM TOKEN"))
    }
}
