package com.landi.mvvmdemo.palyer

class DataListenContainer<T> {
    private val blocks = arrayListOf<(T?)->Unit>()
    var value:T? = null
        get()= field
        set(value:T?) {
            blocks.forEach {
                it.invoke(value)
            }
            field=value
        }
    fun addListener(block:(T?)->Unit){
        if (!blocks.contains(block)){
            blocks.add(block)
        }
    }
}