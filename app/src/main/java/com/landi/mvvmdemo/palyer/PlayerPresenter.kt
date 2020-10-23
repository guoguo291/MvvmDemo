package com.landi.mvvmdemo.palyer

import com.landi.mvvmdemo.domain.Music

class PlayerPresenter private constructor() {

    private val playerModel by lazy {
        PlayerModel()
    }
    private val player by lazy {
        MusicPlayer()
    }

    companion object {
        val instance by lazy {
            PlayerPresenter()
        }
    }

    enum class PlayerState {
        NONE, PALYING, PAUSE, LOADING
    }


    var currentPlayMusic = DataListenContainer<Music>()
    var currentPlayState = DataListenContainer<PlayerState>()

    /**
     * 根据状态播放或者暂停
     */
    fun doPlayOrPause() {
        if (currentPlayMusic.value == null) {
            currentPlayMusic.value = playerModel.getMusicById(id = "卡农")
        }
        player.play(currentPlayMusic.value)
        if (currentPlayState.value != PlayerState.PALYING) {
            currentPlayState.value = PlayerState.PALYING
        } else {
            currentPlayState.value = PlayerState.PAUSE
        }

    }

    fun playPrevious() {
        currentPlayMusic.value=playerModel.getMusicById("上一首：我的好兄弟")
        currentPlayState.value=PlayerState.PALYING
    }

    fun playNext() {
        currentPlayMusic.value=playerModel.getMusicById("下一首：我的中国芯")
        currentPlayState.value=PlayerState.PALYING
    }

}