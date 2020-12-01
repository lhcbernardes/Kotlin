package com.example.application.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(val token: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

            var requestBuilder =
                chain.request().newBuilder().addHeader("Authorization", "Bearer ${token}")

            var request = requestBuilder.build()

            return chain.proceed(request)

    }
}