package com.android.rxJavaDagger.utils.rxbus

interface RxBusCallback {
    fun onEventTrigger(event: Any)
}