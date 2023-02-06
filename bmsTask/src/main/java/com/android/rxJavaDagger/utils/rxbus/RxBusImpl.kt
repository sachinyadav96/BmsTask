package com.android.rxJavaDagger.utils.rxbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBusImpl : RxBus {

    var bus: PublishSubject<Any> = PublishSubject.create()


    override fun toObservable(): Observable<Any> {
        return bus
    }

}