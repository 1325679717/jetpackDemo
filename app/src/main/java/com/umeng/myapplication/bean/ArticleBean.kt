package com.umeng.myapplication.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ArticleInfo(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val curPage: Int,
    val datas: MutableList<DataX>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
@Entity(tableName = "datax")
data class DataX(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "chapter_name")val chapterName: String
)

data class Tag(
    val name: String,
    val url: String
)

enum class State{
    REFRESH//下拉刷新
}