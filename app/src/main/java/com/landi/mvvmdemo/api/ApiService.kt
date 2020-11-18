package com.landi.mvvmdemo.api

import com.landi.mvvmdemo.domain.OnSellData
import com.landi.mvvmdemo.domain.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object{
        const val BASE_URL="https://api.sunofbeach.net/shop/"
    }
    @GET("onSell/{page}")
    suspend fun getOnSellList(@Path("page") page:Int):ResultData<OnSellData>
}