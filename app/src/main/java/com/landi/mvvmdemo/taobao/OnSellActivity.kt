package com.landi.mvvmdemo.taobao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.landi.mvvmdemo.R

class OnSellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_sell)
        val viewModel=ViewModelProvider(this).get(OnSellViewModel::class.java)
        viewModel.loadContent()
    }
}