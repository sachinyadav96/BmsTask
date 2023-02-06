package com.android.rxJavaDagger.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.rxJavaDagger.data.common.NetworkError
import com.android.rxJavaDagger.model.VenueFeedData
import com.android.rxJavaDagger.network.CallbackWrapper
import com.android.rxJavaDagger.network.RestService
import com.android.rxJavaDagger.view.base.BaseNetworkViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(restService: RestService) :
    BaseNetworkViewModel(restService) {

    var newsData: MutableLiveData<VenueFeedData> = MutableLiveData()

    fun getFeedData() {
        getRestService().getVenueFeedData("https://demo2782755.mockable.io/movie_showtimes")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CallbackWrapper<VenueFeedData>() {
                override fun onSuccess(response: VenueFeedData) {
                    newsData.value = response
                }

                override fun onFailure(error: NetworkError) {
                    showSnackBarAction(error.errorMessage!!, "Okay", 0, Snackbar.LENGTH_LONG)
                }
            })
    }
}