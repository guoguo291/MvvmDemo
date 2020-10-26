package com.landi.mvvmdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.landi.mvvmdemo.lifecycle.LifecycleProvider
import com.landi.mvvmdemo.lifecycle.LifecycleState

open class BaseActivity: AppCompatActivity(){
    val lifecycleProvider by lazy {
        LifecycleProvider()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleProvider.makeLifecycleState(LifecycleState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleProvider.makeLifecycleState(LifecycleState.START)
    }
    override fun onResume() {
        super.onResume()
        lifecycleProvider.makeLifecycleState(LifecycleState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleProvider.makeLifecycleState(LifecycleState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleProvider.makeLifecycleState(LifecycleState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleProvider.makeLifecycleState(LifecycleState.DESTROY)
    }
}