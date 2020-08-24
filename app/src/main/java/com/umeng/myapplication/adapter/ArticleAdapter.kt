package com.umeng.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umeng.myapplication.R
import com.umeng.myapplication.bean.DataX
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class ArticleAdapter (val list : MutableList<DataX>) : RecyclerView.Adapter<ArticleAdapter.MyViewHolder>() {

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

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv?.text = list[position].chapterName
    }
}