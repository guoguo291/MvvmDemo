package com.landi.mvvmdemo.musiclist

import android.os.Bundle
import com.landi.mvvmdemo.base.BaseFragment
import com.landi.mvvmdemo.lifecycle.LifecycleState
import com.landi.mvvmdemo.palyer.PlayerPresenter

class MusicListDetailFragment:BaseFragment() {
    val musicListPresenter by lazy {
        MusicListPresenter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}