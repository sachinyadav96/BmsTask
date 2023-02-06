package com.android.rxJavaDagger.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

class RestInterceptor : Interceptor {
    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifiedRequest =
            originalRequest.newBuilder().method(originalRequest.method(), originalRequest.body())
                .build()
        val rb = originalRequest.body()
        val buffer = Buffer()
        rb?.writeTo(buffer)


        val response = chain.proceed(modifiedRequest)
        val responseString = String(response.body()!!.bytes())
        return response.newBuilder()
            .body(ResponseBody.create(response.body()!!.contentType(), responseString)).build()
    }
}