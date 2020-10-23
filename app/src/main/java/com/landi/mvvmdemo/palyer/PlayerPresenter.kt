package com.landi.mvvmdemo.palyer

import com.landi.mvvmdemo.domain.Music

class PlayerPresenter private constructor() {
    private var currentPlayState = PlayerState.NONE
    private var currentPlayMusic:Music?=null
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

    private val callbackList = arrayListOf<IPlayerCallback>()

    fun registercallback(callback: IPlayerCallback) {
        if (!callbackList.contains(callback)) {
            callbackList.add(callback)
        }
    }

    fun unregistercallback(callback: IPlayerCallback) {
        callbackList.remove(callback)
    }

    /**
     * 根据状态播放或者暂停
     */
    fun doPlayOrPause() {
        if (currentPlayMusic == null) {
            currentPlayMusic=playerModel.getMusicById(id = "卡农")
        }
        player.play(currentPlayMusic!!)
        dispatcherTitleState("当前播放的标题")
        dispatcherCoverState("当前播放的封面")
        if (currentPlayState != PlayerState.PALYING) {
            currentPlayState = PlayerState.PALYING
            dispatcherPlayingState()
        } else {
            currentPlayState = PlayerState.PAUSE
            dispatcherPauseState()
        }

    }

    private fun dispatcherPauseState() {
        callbackList.forEach {
            it.onPlayerPause()
        }
    }

    private fun dispatcherPlayingState() {
        callbackList.forEach {
            it.onPlaying()
        }
    }

    private fun dispatcherCoverState(cover: String) {
        callbackList.forEach {
            it.onCoverChanged(cover)
        }
    }

    private fun dispatcherTitleState(title: String) {
        callbackList.forEach {
            it.onTitleChanged(title)
        }
    }

    fun playPrevious() {
        dispatcherTitleState("切换到播放上一首的标题")
        dispatcherCoverState("切换到播放上一首的封面")
    }

    fun playNext() {
        dispatcherTitleState("切换到播放下一首的标题")
        dispatcherCoverState("切换到播放下一首的封面")
    }

}