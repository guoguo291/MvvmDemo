package com.landi.mvvmdemo.lifecycle

/**
 * 抽取LifecycleProvider管理View层生命周期变化通知P层
 */
class LifecycleProvider {
    var mlifecycleListeners = arrayListOf<ILifecycle>()
    private var currentLifecycleState: LifecycleState? = null
    fun addLifecycleListener(listener: ILifecycle) {
        if (!mlifecycleListeners.contains(listener)) {
            mlifecycleListeners.add(listener)
        }
    }

    fun removeLifecycleListener(listener: ILifecycle) {
        mlifecycleListeners.remove(listener)
    }

    fun makeLifecycleState(state: LifecycleState) {
        currentLifecycleState = state
        when (state) {
            LifecycleState.CREATE -> {
                dispatchCreateState()
            }
            LifecycleState.START -> {
                dispatchStartState()
            }
            LifecycleState.RESUME -> {
                dispatchResumeState()
            }
            LifecycleState.PAUSE -> {
                dispatchPauseState()
            }
            LifecycleState.STOP -> {
                dispatchStopState()
            }
            LifecycleState.DESTROY -> {
                dispatchDestroyState()
            }
        }
    }

    private fun dispatchDestroyState() {
        mlifecycleListeners.forEach {
            it.onDestroy()
        }
    }

    private fun dispatchStopState() {
        mlifecycleListeners.forEach {
            it.onStop()
        }
    }

    private fun dispatchPauseState() {
        mlifecycleListeners.forEach {
            it.onPause()
        }
    }

    private fun dispatchResumeState() {
        mlifecycleListeners.forEach {
            it.onResume()
        }
    }

    private fun dispatchStartState() {
        mlifecycleListeners.forEach {
            it.onStart()
        }
    }

    private fun dispatchCreateState() {
        mlifecycleListeners.forEach {
            it.onCreate()
        }
        mlifecycleListeners.clear()
    }
}