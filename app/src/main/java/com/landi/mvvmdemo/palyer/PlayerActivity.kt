package com.landi.mvvmdemo.palyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.landi.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {
    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initListener()
        initDataListener()
    }

    private fun initDataListener() {
        playerPresenter.currentPlayMusic.addListener {
            tv_title.text = it?.title
            println("封面变化了：${it?.cover}")
        }
        playerPresenter.currentPlayState.addListener {
            when (it) {
                PlayerPresenter.PlayerState.PALYING -> {
                    btn_player_playOrpause.text = "暂停"
                    tv_title.text = tv_title.text.toString()+ "..."
                }
                else -> {
                    btn_player_playOrpause.text = "播放"
                    if (tv_title.text.contains("...")){
                        tv_title.text=tv_title.text.substring(0,tv_title.text.length-3)
                    }
                }
            }
        }
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

}