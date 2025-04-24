package com.free.data.datasources

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
//        val token = BuildConfig.GITHUB_TOKEN
//        if (token.isNotEmpty()) {
//            newRequest.header("Authorization", "Bearer ${BuildConfig.GITHUB_TOKEN}")
//        }
        return chain.proceed(newRequest.build())
    }
}