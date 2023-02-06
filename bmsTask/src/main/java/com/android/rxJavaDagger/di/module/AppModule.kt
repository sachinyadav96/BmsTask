package com.android.rxJavaDagger.di.module

import com.android.rxJavaDagger.network.RestApiClient
import com.android.rxJavaDagger.network.RestService
import com.android.rxJavaDagger.utils.rxbus.RxBus
import com.android.rxJavaDagger.utils.rxbus.RxBusImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRxBus(): RxBus {
        return RxBusImpl()
    }

    @Provides
    @Singleton
    fun provideRestService(): RestService {
        val retrofit = RestApiClient.apiClient!!.retrofit
        return retrofit!!.create(RestService::class.java)
    }
}