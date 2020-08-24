package com.umeng.myapplication.ui.main

import com.umeng.myapplication.bean.ArticleInfo
import retrofit2.Response
import retrofit2.http.GET

interface Api{
    @GET("/article/list/0/json")
    suspend fun getArticle() : Response<ArticleInfo>
}