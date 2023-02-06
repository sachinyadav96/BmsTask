package com.android.rxJavaDagger.data.event

class ShowSnackBarEvent(
        val message: String,
        val actionText: String,
        val length: Int? = 0
)