package com.free.data.datasources

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header("Accept", "application/vnd.github.v3+json")
        return chain.proceed(newRequest.build())
    }
}