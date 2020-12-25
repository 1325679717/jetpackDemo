package com.jp.myapplication.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi

class MainViewModel @ViewModelInject constructor(
    val repository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(), DefaultLifecycleObserver {
    private val TAG = "MainViewModel"

    @ExperimentalPagingApi
    fun getArticleList() = repository.getArticleData().asLiveData()


}