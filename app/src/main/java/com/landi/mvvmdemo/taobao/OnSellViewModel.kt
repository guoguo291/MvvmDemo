package com.landi.mvvmdemo.taobao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnSellViewModel : ViewModel() {
    val contentList = MutableLiveData<MutableList<String>>()
}