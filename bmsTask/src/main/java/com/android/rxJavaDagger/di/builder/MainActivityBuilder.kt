package com.android.rxJavaDagger.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.rxJavaDagger.di.factory.BMSViewModelFactory
import com.android.rxJavaDagger.view.home.MainActivity
import com.android.rxJavaDagger.di.key.ViewModelKey
import com.android.rxJavaDagger.view.home.ShowTimeActivity
import com.android.rxJavaDagger.view.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainActivityBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: BMSViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun showTimeActivity(): ShowTimeActivity

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}