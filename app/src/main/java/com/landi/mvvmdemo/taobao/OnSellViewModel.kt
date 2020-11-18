package com.landi.mvvmdemo.taobao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OnSellViewModel : ViewModel() {
    private val onSellRepository by lazy {
        OnsellRepository()
    }
    val contentList = MutableLiveData<MutableList<String>>()
    companion object{
        const val DEFAULT_PAGE=1
    }
    var currentPage= DEFAULT_PAGE
    //加载更多
    fun loadMore(){
        currentPage++
        loadContentByPage(currentPage)
    }

    /**
     * 加载默认页
     */
    fun loadContent(){
        loadContentByPage(DEFAULT_PAGE)
    }
    fun loadContentByPage(page:Int){
        viewModelScope.launch {
            val onSellData = onSellRepository.getOnSellList(page).apiData()
            Log.i("guoj", "onsellData_size: ${onSellData.tbk_dg_optimus_material_response.result_list.map_data.size}")
        }
    }
}