package com.landi.mvvmdemo.taobao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landi.mvvmdemo.domain.OnSellData
import kotlinx.coroutines.launch
import java.lang.Exception

class OnSellViewModel : ViewModel() {
    val contentList =
        MutableLiveData<MutableList<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>>()
    val loadState = MutableLiveData<LoadState>()
    var loadMore = false
    private val onSellRepository by lazy {
        OnsellRepository()
    }

    companion object {
        const val DEFAULT_PAGE = 1
    }

    var currentPage = DEFAULT_PAGE

    //加载更多
    fun loadMore() {
        loadMore=true
        loadState.value = LoadState.LOADMORE_LOADING
        currentPage++
        loadContentByPage(currentPage)
    }

    /**
     * 加载默认页
     */
    fun loadContent() {
        loadMore=false
        loadState.value = LoadState.LOADING
        loadContentByPage(DEFAULT_PAGE)
    }

    fun loadContentByPage(page: Int) {
        viewModelScope.launch {
            try {
                val onSellData = onSellRepository.getOnSellList(page).apiData()
                val oldSellData =contentList.value?: mutableListOf()
                Log.i(
                    "guoj",
                    "onsellData_size: ${onSellData.tbk_dg_optimus_material_response.result_list.map_data.size}"
                )
                oldSellData.addAll(onSellData.tbk_dg_optimus_material_response.result_list.map_data)
                contentList.value = oldSellData
                loadState.value =
                    if (onSellData.tbk_dg_optimus_material_response.result_list.map_data.size == 0) (if (loadMore) LoadState.EMPTY else LoadState.LOADMORE_EMPTY) else LoadState.SUCCESS
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is NullPointerException) {
                    //没有更多内容时候，可能为空指针
                    currentPage--
                    loadState.value=LoadState.LOADMORE_EMPTY
                } else {
                    if(loadMore){
                        currentPage--
                        loadState.value = LoadState.LOADMORE_ERROR
                    }else{
                        loadState.value = LoadState.ERROR
                    }

                }
            }
        }
    }

}