package com.android.rxJavaDagger.network

import android.annotation.SuppressLint
import android.content.Context
import com.android.rxJavaDagger.app.BMSApplication.Companion.getBaseUrl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestApiClient
/**
 * Private constructor so that now one would be able to create its instance
 */
private constructor(apiClientBuilder: ApiClientBuilder) {
    private var mContext: Context? = null
    var retrofit: Retrofit? = null
        private set
    private var mBaseUrl: String? = null
    private var interceptorList: List<Interceptor>? = null
    private var gson: Gson? = null

    init {
        mContext = apiClientBuilder.mContext
        mBaseUrl = apiClientBuilder.mBaseUrl
        interceptorList = apiClientBuilder.interceptorList
        gson = apiClientBuilder.gson
        clientCreation()
    }

    private fun clientCreation() {

        if (gson == null) gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

        val builder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        if (interceptorList != null && interceptorList!!.isNotEmpty()) {
            for (interceptor in interceptorList!!) builder.networkInterceptors().add(interceptor)
        } else {
            builder.networkInterceptors().add(RestInterceptor())
        }
        builder.addInterceptor(logging)

        val okHttpClient = builder.build()

        if (mBaseUrl == null) mBaseUrl = getBaseUrl()
        retrofit = Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * Custom Builder to create rest client
     */
    class ApiClientBuilder {
        var mContext: Context? = null
        var mBaseUrl: String? = null
        var interceptorList: List<Interceptor>? = null
        var gson: Gson? = null
        private var resetClient = false
        fun context(context: Context?): ApiClientBuilder {
            mContext = context
            return this
        }

        fun baseUrl(baseUrl: String?): ApiClientBuilder {
            mBaseUrl = baseUrl
            return this
        }

        fun resetClient(resetClient: Boolean): ApiClientBuilder {
            this.resetClient = resetClient
            return this
        }

        fun build(): RestApiClient? {
            return if (instance == null || resetClient) {
                instance = RestApiClient(this)
                instance
            } else instance
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: RestApiClient? = null

        @JvmStatic
        val apiClient: RestApiClient?
            get() = if (instance != null) instance else throw RuntimeException(
                "Client cannot be null, Please setup client by calling::: new RestApiClient.ApiClientBuilder()"
            )
    }
}