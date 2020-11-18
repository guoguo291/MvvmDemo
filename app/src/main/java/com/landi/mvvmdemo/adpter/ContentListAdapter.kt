package com.landi.mvvmdemo.adpter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.landi.mvvmdemo.R
import com.landi.mvvmdemo.domain.OnSellData
import kotlinx.android.synthetic.main.item_on_sell_content.view.*

class ContentListAdapter: RecyclerView.Adapter<ContentListAdapter.ContentListViewHolder>() {

    val contentList = arrayListOf<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentListViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(
            R.layout.item_on_sell_content,
            parent,
            false
        )
        return ContentListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContentListViewHolder, position: Int) {
        holder.itemView.apply {
            with(contentList[position]){
                on_sell_content_title_tv.text=title
                on_sell_origin_prise_tv.text="￥$zk_final_price"
                on_sell_origin_prise_tv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
                val finalPrise: Float = zk_final_price.toFloat()-coupon_amount
                on_sell_off_prise_tv.text= "券后价：${String.format("%.2f", finalPrise)}"
                Glide.with(context).load("https:$pict_url").into(on_sell_cover)
            }
        }
    }

    override fun getItemCount(): Int = contentList.size

    class ContentListViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }
    fun setData(list: List<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>){
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }

}