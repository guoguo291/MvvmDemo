package com.landi.mvvmdemo.palyer

class DataListenContainer<T> {
    private val blocks = arrayListOf<(T?)->Unit>()
    var value:T?=null
        set(value:T?) {
            blocks.forEach {
                it.invoke(value)
            }
        }
    fun addListener(block:(T?)->Unit){
        if (!blocks.contains(block)){
            blocks.add(block)
        }
    }
}