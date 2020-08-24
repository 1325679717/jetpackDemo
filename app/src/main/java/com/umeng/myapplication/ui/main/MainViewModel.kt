package com.umeng.myapplication.ui.main

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.umeng.myapplication.bean.DataX
import com.umeng.myapplication.bean.State
import com.umeng.myapplication.db.DataDao
import kotlinx.coroutines.launch
import retrofit2.Retrofit
class MainViewModel @ViewModelInject constructor(
    private val dataDao: DataDao,
    private val retrofit: Retrofit,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(), DefaultLifecycleObserver {
    private val _listLiveData : MutableLiveData<MutableList<DataX>> = MutableLiveData()

    val listLiveData = _listLiveData

    private val _stateLiveData : MutableLiveData<State> = MutableLiveData()
    val stateLiveData = _stateLiveData

    override fun onCreate(owner: LifecycleOwner) {
        loadData(false)
    }

    fun loadData(isRefresh : Boolean){
        viewModelScope.launch {

            val list = dataDao.getAllData()
            if (list.size > 0 && !isRefresh){
                Log.d("ViewModel","本地缓存数据 list size = ${list.size}")
                listLiveData.value = list
                return@launch
            }

            val response = retrofit.create(Api::class.java).getArticle()
            if (response.body()?.errorCode == 0) {
                dataDao.deleteAll()
                response.body()?.data?.datas?.let { dataDao.insertAll(it) }
                listLiveData.value = response.body()?.data?.datas
            }
            if (isRefresh){
                _stateLiveData.value = State.REFRESH
            }
        }
    }

}