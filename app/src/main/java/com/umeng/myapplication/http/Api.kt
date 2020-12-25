package com.umeng.myapplication.http

import com.umeng.myapplication.bean.ArticleInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import java.sql.Array

interface Api{
    @GET("/article/list/{page}/json")
//    @Headers(value = ["Headerkey:value"])
    suspend fun getArticle(@Path("page") page :Int) : Response<ArticleInfo>
}