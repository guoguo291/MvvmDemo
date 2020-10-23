package com.landi.mvvmdemo.musiclist

import com.landi.mvvmdemo.domain.Music

class MusicListModel {
    var musicList = ArrayList<Music>()
    fun getMusicList(page: Int, size: Int,listener:OnMusicListLoadResultListerner) {
        //模拟获取音乐信息
        Thread{
            for (i in (0 until size)){
                musicList.add(Music("音乐名:$i","音乐封面:$i","地址：$i"))
            }
            listener.onloadSuccess(musicList)
        }.start()
    }
    interface OnMusicListLoadResultListerner{
        fun onloadSuccess(list:ArrayList<Music>)
        fun onloadError(msg:String,errorCode:Int)
    }

}