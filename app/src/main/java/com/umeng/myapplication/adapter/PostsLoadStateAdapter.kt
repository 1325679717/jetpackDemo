package com.umeng.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umeng.myapplication.R

class PostsLoadStateAdapter (private val adapter: ArticleAdapter) :LoadStateAdapter<NetworkStateItemViewHolder>(){
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }


}
class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {
    private val TAG = "PostsLoadStateAdapter"
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.network_progressBar)
    fun bindTo(loadState: LoadState) {
        Log.d(TAG,"loadState = $loadState")
        progressBar.isVisible = loadState is LoadState.Loading
    }

}
