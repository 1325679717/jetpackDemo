package com.umeng.myapplication.module

import android.util.Log
import com.umeng.myapplication.adapter.ArticleAdapter
import com.umeng.myapplication.bean.DataX
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped


@Module//ActivityRetainedComponent
@InstallIn(FragmentComponent::class)
object ArticleMudule{
    @FragmentScoped
    @Provides
    fun provideArticleAdapter() : ArticleAdapter {
        val articleAdapter = ArticleAdapter()
        return articleAdapter
    }
//    @FragmentScoped
//    @Provides
//    fun provideList() : MutableList<DataX>{
//        return arrayListOf()
//    }
}