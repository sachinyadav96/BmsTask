package com.android.rxJavaDagger.app

import android.content.Context
import androidx.multidex.MultiDex
import com.android.rxJavaDagger.di.component.DaggerAppComponent
import com.android.rxJavaDagger.network.RestApiClient
import com.android.rxJavaDagger.utils.BMSConstants
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BMSApplication : DaggerApplication() {
    init {
        instance = this
    }

    companion object {
        private var instance: BMSApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun getBaseUrl(): String {
            return BMSConstants.BASE_URL
        }


    }

    override fun onCreate() {
        super.onCreate()
        initSetup()
    }

    private fun initSetup() {
        setUpClient()
    }

    override fun applicationInjector(): AndroidInjector<out BMSApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setUpClient() {
        RestApiClient.ApiClientBuilder().baseUrl(getBaseUrl()).resetClient(false).build()
    }


}