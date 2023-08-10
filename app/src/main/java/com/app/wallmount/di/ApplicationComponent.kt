package com.app.wallmount.di

import com.app.wallmount.controller.activity.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
}