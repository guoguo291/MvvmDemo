package com.landi.mvvmdemo.taobao

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.landi.mvvmdemo.R
import com.landi.mvvmdemo.adpter.ContentListAdapter
import kotlinx.android.synthetic.main.activity_on_sell.*

class OnSellActivity : AppCompatActivity() {
    private val mViewModel by lazy {
        ViewModelProvider(this).get(OnSellViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_sell)
        initView()
        initObserver()

    }
    private val mAdapter by lazy {
        ContentListAdapter()
    }
    private fun initObserver() {
        mViewModel.apply {
            contentList.observe(this@OnSellActivity, Observer {
                //观察数据变化
                mAdapter.setData(it)
            })
        }.loadContent()
    }

    private fun initView() {
        contentListRv.apply {
            layoutManager=GridLayoutManager(this@OnSellActivity,2)
            adapter=mAdapter
            addItemDecoration(object:RecyclerView.ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.apply {
                        top=8
                        left=8
                        right=8
                        bottom=8
                    }
                }
            })
        }
    }
}