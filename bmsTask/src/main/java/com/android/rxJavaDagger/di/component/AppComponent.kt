package com.android.rxJavaDagger.di.component

import com.android.rxJavaDagger.app.BMSApplication
import com.android.rxJavaDagger.di.builder.MainActivityBuilder
import com.android.rxJavaDagger.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, MainActivityBuilder::class]
)
interface AppComponent : AndroidInjector<BMSApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BMSApplication>()
}