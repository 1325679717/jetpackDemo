package com.jp.myapplication.module

import com.jp.myapplication.adapter.ArticleAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped


@Module//ActivityRetainedComponent
@InstallIn(FragmentComponent::class)
object ArticleMudule{
    @FragmentScoped
    @Provides
    fun provideArticleAdapter() : ArticleAdapter {
        return ArticleAdapter()
    }

//    @FragmentScoped
//    @Provides
//    fun provideList() : MutableList<DataX>{
//        return arrayListOf()
//    }
}