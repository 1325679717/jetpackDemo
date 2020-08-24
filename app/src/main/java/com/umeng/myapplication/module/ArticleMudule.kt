package com.umeng.myapplication.module

import android.util.Log
import com.umeng.myapplication.adapter.ArticleAdapter
import com.umeng.myapplication.bean.DataX
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module//ActivityRetainedComponent
@InstallIn(ActivityComponent::class)
object ArticleMudule{
    @ActivityScoped
    @Provides
    fun provideArticleAdapter(list : MutableList<DataX>) : ArticleAdapter {
        val articleAdapter = ArticleAdapter(list)
    Log.d("ArticleMudule"," ${articleAdapter}")
        return articleAdapter
    }
    @ActivityScoped
    @Provides
    fun provideList() : MutableList<DataX>{
        return arrayListOf()
    }
}