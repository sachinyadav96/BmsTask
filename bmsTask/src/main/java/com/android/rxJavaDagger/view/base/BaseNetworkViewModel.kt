package com.android.rxJavaDagger.view.base

import com.android.rxJavaDagger.data.event.ShowSnackBarEvent
import com.android.rxJavaDagger.network.RestService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

open class BaseNetworkViewModel(private var inRestService: RestService) : BaseViewModel() {

    private var compositeDisposable: CompositeDisposable? = null

    val showSnackBarEventObservable: PublishSubject<ShowSnackBarEvent> = PublishSubject.create()

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }

    fun getRestService(): RestService {
        return this.inRestService
    }

    fun showSnackBarAction(message: String, actionText: String, requestCode: Int, length: Int?) {
        showSnackBarEventObservable.onNext(ShowSnackBarEvent(message, actionText, length))
    }
}