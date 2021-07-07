package com.example.learningmviarchitecture

import android.app.Application
import com.example.learningmviarchitecture.di.AppComponent
import com.example.learningmviarchitecture.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApp : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().app(this).build()
        return appComponent
    }
}