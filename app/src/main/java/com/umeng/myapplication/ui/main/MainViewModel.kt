package com.umeng.myapplication.ui.main

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.umeng.myapplication.bean.DataX
import com.umeng.myapplication.bean.State
import com.umeng.myapplication.db.DataDao
import kotlinx.coroutines.launch
import retrofit2.Retrofit
class MainViewModel @ViewModelInject constructor(
    val repository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(), DefaultLifecycleObserver {
    private val TAG = "MainViewModel"

    fun getArticleList() = repository.getArticleData().asLiveData()


}