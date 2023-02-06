package com.android.rxJavaDagger.utils.rxbus

import android.annotation.SuppressLint
import io.reactivex.observers.DisposableObserver


class RxBusHelper {
    private var disposable: DisposableObserver<Any>? = null

    @SuppressLint("CheckResult")
    fun registerEvents(rxBus: RxBus?, tag: String, callback: RxBusCallback) {

        if (rxBus != null) {
            disposable = object : DisposableObserver<Any>() {
                override fun onNext(event: Any) {
                    callback.onEventTrigger(event)
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }
            }
            rxBus.toObservable().share().subscribeWith(disposable)
        }
    }

    fun unSubScribe() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
            disposable = null
        }
    }
}