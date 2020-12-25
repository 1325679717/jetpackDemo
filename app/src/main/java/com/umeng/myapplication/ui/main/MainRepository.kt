package com.umeng.myapplication.ui.main

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.umeng.myapplication.bean.DataX
import com.umeng.myapplication.db.DataDao
import retrofit2.Retrofit
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataDao: DataDao,
    private val retrofit: Retrofit){
    fun getArticleData() = Pager(PagingConfig(pageSize = 21)){
        ArticleDataSource(retrofit)
    }.flow

    class ArticleDataSource(val retrofit: Retrofit) : PagingSource<Int, DataX>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataX> {
            val page = params.key ?:0
            var data : MutableList<DataX> ?= null
            try {
                val response = retrofit.create(Api::class.java).getArticle(page)
                if (response.isSuccessful){
                    if (response.body()?.errorCode == 0) {
                        data = response.body()?.data?.datas
                    }
                }
//                Log.d("ArticleDataSource","loadData = ${data}")
                return LoadResult.Page(
                    data = data!!,
                    prevKey = null,
                    nextKey = if (response.body()!!.data.curPage == response.body()!!.data.pageCount) null else (page+1)
                )
            }catch (e :Exception){
                return LoadResult.Error(e)
            }
        }

    }
}