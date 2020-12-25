package com.jp.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.jp.myapplication.R
import com.jp.myapplication.adapter.ArticleAdapter
import com.jp.myapplication.adapter.PostsLoadStateAdapter
import com.jp.myapplication.bean.DataX
import com.jp.myapplication.bean.State
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(){

    private val TAG = "MainFragment"
    companion object {
        fun newInstance() = MainFragment()
    }
    protected var rootView :View ?= null

    private val viewModel: MainViewModel by viewModels()
    @Inject lateinit var adapter : ArticleAdapter

    private var refreshLayout : SmartRefreshLayout?= null

    private inline fun <reified T : View> findViewById(id :Int) : T? {
        return rootView?.findViewById(id)
    }

    @ExperimentalPagingApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.main_fragment, container, false)

        lifecycle.addObserver(viewModel)

        val rv = findViewById<RecyclerView>(R.id.mainRv)

        rv?.adapter = adapter.withLoadStateFooter(PostsLoadStateAdapter(adapter))

        refreshLayout = findViewById(R.id.refreshlayout)

        refreshLayout?.setOnRefreshListener {
            adapter.refresh()
        }

        viewModel.getArticleList().observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle,it)
        })
        adapter.addLoadStateListener {
            if (it.refresh is LoadState.NotLoading && refreshLayout?.isRefreshing!!){
                refreshLayout?.finishRefresh()
            }
        }
        return rootView
    }


    fun onData(data : MutableList<DataX>){
        adapter.notifyDataSetChanged()
    }

    fun onChangeState(state : State){
        if (state == State.REFRESH){
            refreshLayout?.finishRefresh()
        }
    }

}