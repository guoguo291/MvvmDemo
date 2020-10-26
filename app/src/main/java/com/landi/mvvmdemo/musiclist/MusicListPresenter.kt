package com.landi.mvvmdemo.musiclist

import android.util.Log
import com.landi.mvvmdemo.ILifecycle
import com.landi.mvvmdemo.domain.Music
import com.landi.mvvmdemo.palyer.DataListenContainer

class MusicListPresenter:ILifecycle {
    companion object{
        val instance by lazy {
            MusicListPresenter()
        }
    }
    private val musicListModel by lazy {
        MusicListModel()
    }
    enum class MusicListState{
        LOADING,EMPTY,SUCCESS,ERROR
    }
    var musicList=DataListenContainer<List<Music>>()
    var musicListloadState=DataListenContainer<MusicListState>()
    fun getMusicList(page: Int, size: Int) {
        musicListloadState.value=MusicListState.LOADING
        musicListModel.getMusicList(page,size,object :MusicListModel.OnMusicListLoadResultListerner{
            override fun onloadSuccess(list: ArrayList<Music>) {
                musicList.value=list
                musicListloadState.value=if (list?.isEmpty()){
                    MusicListState.EMPTY
                }else{
                    MusicListState.SUCCESS
                }
            }

            override fun onloadError(msg: String, errorCode: Int) {
                musicListloadState.value=MusicListState.ERROR
                println("error..$msg..$errorCode")
            }

        })
    }

    override fun onCreate() {
        Log.i("guoj","监听网络变化")
    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {
        Log.i("guoj","停止监听网络变化")
    }


}