package com.android.rxJavaDagger.view.base

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
 import com.android.rxJavaDagger.app.BMSApplication
import com.android.rxJavaDagger.data.common.NetworkError
import com.android.rxJavaDagger.data.common.UiHelper
import com.android.rxJavaDagger.R
import io.reactivex.subjects.PublishSubject

open class BaseViewModel : ViewModel() {

    var uiLiveData: MutableLiveData<UiHelper> = MutableLiveData()

    var bottomLoadingObservable: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    var progressBottom: ObservableField<Boolean> = ObservableField()

    protected fun showProgress() {
        uiLiveData.value = UiHelper.UiHelperBuilder()
            .showProgress(true)
            .build()
    }

    protected fun showProgress(message: String) {
        var msg = message
        if (message.isEmpty()) {
            msg = BMSApplication.applicationContext().getString(R.string.please_wait)
        }
        uiLiveData.value = UiHelper.UiHelperBuilder()
            .showProgress(true)
            .showMessage(msg)
            .build()
    }


    protected fun hideProgress() {
        uiLiveData.value = UiHelper.UiHelperBuilder().showProgress(false).build()

    }

    protected fun setErrorCode(error: NetworkError) {
        val helper = UiHelper.UiHelperBuilder().setError(error).build()
        uiLiveData.value = helper
    }


}