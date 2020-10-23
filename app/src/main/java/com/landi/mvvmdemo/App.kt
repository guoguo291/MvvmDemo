package com.landi.mvvmdemo

import android.app.Application
import android.os.Handler

class App : Application() {
    companion object {
        val handler = Handler()
    }

    override fun onCreate() {
        super.onCreate()
    }
}