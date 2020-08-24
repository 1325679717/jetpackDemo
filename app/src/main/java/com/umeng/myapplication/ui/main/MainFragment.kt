package com.umeng.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.umeng.myapplication.R
import com.umeng.myapplication.adapter.ArticleAdapter
import com.umeng.myapplication.bean.DataX
import com.umeng.myapplication.bean.State
import com.umeng.myapplication.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(){

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    @Inject lateinit var list : MutableList<DataX>
    @Inject lateinit var adapter : ArticleAdapter

    private var refreshLayout : SmartRefreshLayout ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        lifecycle.addObserver(viewModel)

        observe(viewModel.listLiveData,this::onData)

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.mainRv)
        rv.adapter = adapter

        refreshLayout = view.findViewById<SmartRefreshLayout>(R.id.refreshlayout)
        refreshLayout?.setOnRefreshListener {
            viewModel.loadData(true)
        }

        observe(viewModel.stateLiveData,this::onChangeState)
        return view
    }

    fun onData(data : MutableList<DataX>){
        list.clear()
        list.addAll(data)
        adapter?.notifyDataSetChanged()
    }

    fun onChangeState(state : State){
        if (state == State.REFRESH){
            refreshLayout?.finishRefresh()
        }
    }

}