package com.landi.mvvmdemo.taobao

import com.landi.mvvmdemo.api.RetrofitClient
import com.landi.mvvmdemo.domain.OnSellData
import com.landi.mvvmdemo.domain.ResultData

class OnsellRepository {
    suspend fun getOnSellList(page:Int): ResultData<OnSellData> =
        RetrofitClient.apiService.getOnSellList(page)
}