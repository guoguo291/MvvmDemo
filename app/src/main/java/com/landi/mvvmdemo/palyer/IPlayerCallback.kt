package com.landi.mvvmdemo.palyer

interface IPlayerCallback {
    fun onPlaying()
    fun onTitleChanged(title: String)
    fun onProgressChanged(progerss: Int)
    fun onCoverChanged(cover: String)
    fun onPlayerPause()
}
