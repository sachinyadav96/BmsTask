package com.android.rxJavaDagger.network

import com.android.rxJavaDagger.model.VenueFeedData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RestService {
    @GET
    fun getVenueFeedData(@Url url: String?): Observable<Response<VenueFeedData?>?>?
}