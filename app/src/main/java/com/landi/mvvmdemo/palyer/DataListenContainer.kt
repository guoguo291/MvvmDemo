package com.landi.mvvmdemo.palyer

import android.os.Looper
import com.landi.mvvmdemo.App

class DataListenContainer<T> {
    private val blocks = arrayListOf<(T?)->Unit>()
    var value:T? = null
        get()= field
        set(value:T?) {
            //判断是否是主线程
            if (isMainthread()){
                blocks.forEach {
                    it.invoke(value)
                }
            }else{
                App.handler.post {
                    blocks.forEach {
                        it.invoke(value)
                    }
                }
            }
            field=value
        }

    private fun isMainthread(): Boolean = Looper.getMainLooper().thread==Thread.currentThread()

    fun addListener(block:(T?)->Unit){
        if (!blocks.contains(block)){
            blocks.add(block)
        }
    }
}