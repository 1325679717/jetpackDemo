package com.jp.myapplication.http

import com.jp.myapplication.bean.ArticleInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api{
    @GET("/article/list/{page}/json")
//    @Headers(value = ["Headerkey:value"])
    suspend fun getArticle(@Path("page") page :Int) : Response<ArticleInfo>
}