package com.jp.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jp.myapplication.R
import com.jp.myapplication.bean.DataX

class ArticleAdapter : PagingDataAdapter<DataX,ArticleAdapter.MyViewHolder>(diff) {
    private val TAG =  "ArticleAdapter"
    companion object{
        val diff = object : DiffUtil.ItemCallback<DataX>(){
            override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv : TextView?= null
        init {
            tv = itemView.findViewById(R.id.tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent,false)
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv?.text = getItem(position)?.title
        Log.d(TAG,"onBindViewHolder")
    }
}