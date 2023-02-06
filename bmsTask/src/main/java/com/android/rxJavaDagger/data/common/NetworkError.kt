package com.android.rxJavaDagger.data.common

data class NetworkError(var errorCode: String? = null, var errorTitle: String? = null, var errorMessage: String? = null, var statusCode: Int = 400)