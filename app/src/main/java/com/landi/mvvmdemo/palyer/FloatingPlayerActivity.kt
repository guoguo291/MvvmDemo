package com.landi.mvvmdemo.palyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.landi.mvvmdemo.R
import kotlinx.android.synthetic.main.activity_floating_player.*

class FloatingPlayerActivity : AppCompatActivity() {
    val presenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_player)
        initListener()
        initDataListener()
    }

    private fun initListener() {
        btn_floating_play.setOnClickListener {
            presenter.doPlayOrPause()
        }
    }

    private fun initDataListener() {
        presenter.currentPlayState.addListener {
            btn_floating_play.text=if (it===PlayerPresenter.PlayerState.PALYING){
                "暂停"
            }else{
                "播放"
            }
        }
    }

}