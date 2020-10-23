package com.landi.mvvmdemo.palyer

import com.landi.mvvmdemo.domain.Music

class PlayerModel {
    fun getMusicById(id: String) = Music("歌曲名:$id","cover","www.baidu.com")

}