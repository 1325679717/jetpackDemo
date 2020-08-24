package com.umeng.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.umeng.myapplication.bean.DataX

@Dao
interface DataDao{
    @Query("SELECT * FROM DataX")
    suspend fun getAllData() : MutableList<DataX>

    @Insert
    suspend fun insertAll(data : MutableList<DataX>)


    @Query("DELETE FROM datax")
    suspend fun deleteAll()
}