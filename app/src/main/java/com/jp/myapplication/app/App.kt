package com.jp.myapplication.app

import android.app.Application
import com.jp.myapplication.utils.LogUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object{
        var instance : App?=null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
         LogUtil.init(true)
    }
}