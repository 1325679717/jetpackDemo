package com.umeng.myapplication.ui.main

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.umeng.myapplication.bean.DataX
import com.umeng.myapplication.db.DataDao
import com.umeng.myapplication.http.Api
import retrofit2.Retrofit
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataDao: DataDao,
    private val retrofit: Retrofit){
    fun getArticleData() = Pager(PagingConfig(pageSize = 21)){
        ArticleDataSource(dataDao,retrofit)
    }.flow

    class ArticleDataSource(private val dataDao: DataDao,val retrofit: Retrofit) : PagingSource<Int, DataX>() {
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