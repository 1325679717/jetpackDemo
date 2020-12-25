package com.jp.myapplication.ui.main

import androidx.paging.*
import androidx.room.withTransaction
import com.jp.myapplication.bean.Data
import com.jp.myapplication.bean.DataX
import com.jp.myapplication.db.AppDatabase
import com.jp.myapplication.db.DataDao
import com.jp.myapplication.http.Api
import com.jp.myapplication.utils.LogUtil
import retrofit2.Retrofit
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val dataDao: DataDao,
    private val retrofit: Retrofit){
    @ExperimentalPagingApi
    fun getArticleData() = Pager(PagingConfig(pageSize = 21),remoteMediator = RemoteDataSource(appDatabase,dataDao,retrofit)){
        dataDao.getAllData()
    }.flow

    class ArticleDataSource(private val dataDao: DataDao, private val retrofit: Retrofit) : PagingSource<Int, DataX>() {
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

    @ExperimentalPagingApi
    class RemoteDataSource(private val appDatabase: AppDatabase,
                           private val dataDao: DataDao,
                           private val retrofit: Retrofit) : RemoteMediator<Int,DataX>(){
        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, DataX>
        ): MediatorResult {
            try {

                val pageKey = when (loadType) {
                    LoadType.REFRESH -> null

                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                            ?: return MediatorResult.Success(endOfPaginationReached = true)
                        lastItem.page

                    }
                }
                if (false){//无网络
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                val  page = pageKey ?: 0
                val response = retrofit.create(Api::class.java).getArticle(page * state.config.pageSize)
                var endOfPaginationReached = true
                if (response.isSuccessful){
                    val result = response.body()?.data?.datas
                    endOfPaginationReached = result?.isEmpty() ?:true
                    val item = result?.map {
                        DataX(
                            id = it.id,
                            chapterName = it.chapterName,
                            page = page +1,
                            title = it.title
                        )
                    }
                    appDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH){
                            dataDao.deleteAll()
                        }
//                        val nextKey = if (endOfPaginationReached) null else page +1
//                        val entity = DataX
                        item?.let { dataDao.insertAll(it) }
                    }
                }
                LogUtil.d("RemoteDataSource try return endOfPaginationReached = $endOfPaginationReached")
                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }catch (e :Exception) {
                return MediatorResult.Error(e)
            }
        }

    }
}