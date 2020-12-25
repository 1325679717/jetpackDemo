package com.umeng.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umeng.myapplication.R
import com.umeng.myapplication.bean.DataX
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

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
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, null)
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv?.text = getItem(position)?.title
        Log.d(TAG,"onBindViewHolder")
    }
}