package com.android.rxJavaDagger.utils.rxbus

import io.reactivex.Observable

interface RxBus {
    fun toObservable(): Observable<Any>
}