package com.landi.mvvmdemo.palyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.landi.mvvmdemo.R

class FloatingPlayerActivity : AppCompatActivity(), IPlayerCallback {
    val presenter by lazy {
        PlayerPresenter.instance
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_player)
        presenter.registercallback(this)
    }

    override fun onPlaying() {
        TODO("Not yet implemented")
    }

    override fun onTitleChanged(title: String) {
        TODO("Not yet implemented")
    }

    override fun onProgressChanged(progerss: Int) {
        TODO("Not yet implemented")
    }

    override fun onCoverChanged(cover: String) {
        TODO("Not yet implemented")
    }

    override fun onPlayerPause() {
        TODO("Not yet implemented")
    }
}