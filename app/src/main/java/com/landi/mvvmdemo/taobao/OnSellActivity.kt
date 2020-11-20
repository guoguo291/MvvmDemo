package com.landi.mvvmdemo.taobao

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.landi.mvvmdemo.R
import com.landi.mvvmdemo.adpter.ContentListAdapter
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import kotlinx.android.synthetic.main.activity_error.*
import kotlinx.android.synthetic.main.activity_on_sell.*
import java.time.Duration

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
            contentList.observe(this@OnSellActivity, {
                //观察数据变化
                mAdapter.setData(it)
            })
            loadState.observe(this@OnSellActivity, {
                //根据状态替换UI显示
                if (it != LoadState.LOADMORE_LOADING) {
                    hideAll()
                }
                when (it) {
                    LoadState.SUCCESS -> {
                        view_refresh_content.visibility = View.VISIBLE
                        view_refresh_content.finishLoadmore()
                    }
                    LoadState.ERROR -> {
                        view_error.visibility = View.VISIBLE
                        view_refresh_content.finishLoadmore()
                    }
                    LoadState.EMPTY -> {
                        view_empty.visibility = View.VISIBLE
                        view_refresh_content.finishLoadmore()
                    }
                    LoadState.LOADMORE_EMPTY -> {
                        view_refresh_content.visibility = View.VISIBLE
                        view_refresh_content.finishLoadmore()
                        Toast.makeText(this@OnSellActivity, "没有更多内容了...", Toast.LENGTH_SHORT).show()
                    }
                    LoadState.LOADMORE_ERROR -> {
                        view_refresh_content.visibility = View.VISIBLE
                        view_refresh_content.finishLoadmore()
                        Toast.makeText(this@OnSellActivity, "网络状况异常，稍后重试", Toast.LENGTH_SHORT)
                            .show()
                    }
                    LoadState.LOADMORE_LOADING -> {
                        view_refresh_content.visibility = View.VISIBLE
                        view_refresh_content.startLoadMore()
                    }
                    else -> view_loading.visibility = View.VISIBLE
                }
            })
        }.loadContent()
    }

    private fun hideAll() {
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
        view_loading.visibility = View.GONE
        view_refresh_content.visibility = View.GONE
    }

    private fun initView() {
        view_refresh_content.run {
            setEnableRefresh(false)
            setEnableLoadmore(true)
            setEnableOverScroll(false)
            setOnRefreshListener(object : RefreshListenerAdapter() {
                override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                    mViewModel.loadMore()
                }
            })
        }
        iv_error.setOnClickListener {
            mViewModel.loadContent()
        }
        contentListRv.apply {
            layoutManager = GridLayoutManager(this@OnSellActivity, 2)
            adapter = mAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.apply {
                        top = 8
                        left = 8
                        right = 8
                        bottom = 8
                    }
                }
            })
        }
    }
}