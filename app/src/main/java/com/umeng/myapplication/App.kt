package com.umeng.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object{
        var instance : App?=null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}