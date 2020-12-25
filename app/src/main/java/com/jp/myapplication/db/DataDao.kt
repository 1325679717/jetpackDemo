package com.jp.myapplication.db

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jp.myapplication.bean.DataX

@Dao
interface DataDao{
    @Query("SELECT * FROM datax")
    fun getAllData() : PagingSource<Int, DataX>

    @Insert
    suspend fun insertAll(data : List<DataX>)


    @Query("DELETE FROM datax")
    suspend fun deleteAll()

    @Update
    suspend fun updateAll(data : List<DataX>)
}