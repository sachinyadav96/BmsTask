package com.android.rxJavaDagger.view.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.android.rxJavaDagger.data.event.ShowSnackBarEvent
import com.android.rxJavaDagger.utils.rxbus.RxBus
import com.android.rxJavaDagger.utils.rxbus.RxBusCallback
import com.android.rxJavaDagger.utils.rxbus.RxBusHelper
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), RxBusCallback {
    private val TAG = BaseActivity::class.java.simpleName

    @Inject
    protected lateinit var rxBus: RxBus

    private var rxBusHelper: RxBusHelper? = null

    private val baseDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObservingLiveData()
    }


    private fun getRootView(): View? {
        var rootView: View? = null
        try {
            val contentViewGroup = findViewById<ViewGroup>(android.R.id.content)
            rootView = null

            if (contentViewGroup != null) rootView = contentViewGroup.getChildAt(0)

            if (rootView == null) rootView = window.decorView.rootView
        } catch (e: Exception) {
            e.toString()
        }

        return rootView
    }

    private fun showSnackBar(snackBarEvent: ShowSnackBarEvent) {
        if (getRootView() != null) {
            val snackBar =
                Snackbar.make(getRootView()!!, snackBarEvent.message, snackBarEvent.length!!)
            snackBar.show()
        }
    }


    override fun onResume() {
        super.onResume()
        registerEvents()
    }

    override fun onPause() {
        super.onPause()
        unregisterEvents()
    }

    private fun registerEvents() {
        if (rxBusHelper == null) {
            rxBusHelper = RxBusHelper()
            rxBusHelper?.registerEvents(rxBus, TAG, this)
        }
    }

    private fun unregisterEvents() {
        rxBusHelper?.unSubScribe()
        rxBusHelper = null
    }

    override fun onEventTrigger(event: Any) {
        handleEvents(event)
    }

    open fun handleEvents(event: Any) {
        //Sub class can override this method if required
    }

    open fun getViewModel(): BaseViewModel? {
        //Sub class can override this method if required
        return null
    }

    @CallSuper
    open fun startObservingLiveData() {
        (getViewModel() as? BaseNetworkViewModel)?.showSnackBarEventObservable?.subscribe { event ->
            showSnackBar(
                event
            )
        }?.let {
            baseDisposable.add(it)
        }
    }


    override fun onDestroy() {
        baseDisposable.clear()
        super.onDestroy()
    }


    override fun onBackPressed() {

        super.onBackPressed()

    }

}