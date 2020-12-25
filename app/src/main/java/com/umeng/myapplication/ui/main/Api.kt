package com.umeng.myapplication.ui.main

import com.umeng.myapplication.bean.ArticleInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api{
    @GET("/article/list/{page}/json")
    suspend fun getArticle(@Path("page") page :Int) : Response<ArticleInfo>
}