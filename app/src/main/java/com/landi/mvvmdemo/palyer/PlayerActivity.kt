package com.landi.mvvmdemo.palyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.landi.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), IPlayerCallback {
    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerPresenter.registercallback(this)
        initListener()
        initDataListener()
    }

    private fun initDataListener() {

    }

    private fun initListener() {
        btn_player_playOrpause.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
        btn_palyer_pre.setOnClickListener {
            playerPresenter.playPrevious()
        }
        btn_player_next.setOnClickListener {
            playerPresenter.playNext()
        }
    }

    override fun onDestroy() {
        playerPresenter.unregistercallback(this)
        super.onDestroy()
    }
    override fun onPlaying() {
        btn_player_playOrpause.text="暂停"
    }

    override fun onTitleChanged(title: String) {
        tv_title.text=title
    }

    override fun onProgressChanged(progerss: Int) {

    }

    override fun onCoverChanged(cover: String) {
        println(cover)
    }

    override fun onPlayerPause() {
        btn_player_playOrpause.text="播放"
    }
}