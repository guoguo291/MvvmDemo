package com.landi.mvvmdemo.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.landi.mvvmdemo.ILifecycle

open class BaseActivity: AppCompatActivity(){
    var mlifecycleListeners = arrayListOf<ILifecycle>()
    fun addLifecycleListener(listener:ILifecycle){
        if (!mlifecycleListeners.contains(listener)){
            mlifecycleListeners.add(listener)
        }
    }
    fun removeLifecycleListener(listener:ILifecycle){
        mlifecycleListeners.remove(listener)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mlifecycleListeners.forEach {
            it.onCreate()
        }
    }

    override fun onStart() {
        super.onStart()
        mlifecycleListeners.forEach {
            it.onStart()
        }
    }
    override fun onResume() {
        super.onResume()
        mlifecycleListeners.forEach {
            it.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        mlifecycleListeners.forEach {
            it.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        mlifecycleListeners.forEach {
            it.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mlifecycleListeners.forEach {
            it.onDestroy()
        }
    }
}