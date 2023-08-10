package com.app.wallmount.controller.activity

import android.app.Application
import com.app.wallmount.di.ApplicationComponent
import com.app.wallmount.di.DaggerApplicationComponent

class MyApp : Application()  {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()


        applicationComponent=DaggerApplicationComponent.builder().build()

    }
}