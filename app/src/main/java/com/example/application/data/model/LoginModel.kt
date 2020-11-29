package com.example.application.data.model

class LoginModel(userName: String, password: String, token: String) {
    var email: String? = null
    var password: String? = null
    var token: String? = null

    init {
        this.email = userName
        this.password = password
        this.token = token
    }
}