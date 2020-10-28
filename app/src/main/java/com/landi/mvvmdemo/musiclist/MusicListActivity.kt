package com.landi.mvvmdemo.musiclist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.mbms.MbmsErrors
import android.util.Log
import com.landi.mvvmdemo.R
import com.landi.mvvmdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_music_list.*

class MusicListActivity : BaseActivity() {
    private val musicListPresenter by lazy {
        MusicListPresenter(this)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)
        initListener()
        initDataListener()
    }

    private fun initDataListener() {
        musicListPresenter.musicList.addListener {
            println(Thread.currentThread().name)
            println("${it?.size}")
            tv_music_count.text = "获取到歌曲：${it?.size}首"
        }
        musicListPresenter.musicListloadState.addListener {
            println("加载状态。。。$it")
        }
    }

    var page = 1
    var size = 10
    private fun initListener() {
        btn_load_list.setOnClickListener {
            musicListPresenter.getMusicList(page, size)
        }
    }

}