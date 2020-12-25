package com.umeng.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.umeng.myapplication.bean.DataX

@Dao
interface DataDao{
    @Query("SELECT * FROM datax")
    suspend fun getAllData() : MutableList<DataX>

    @Insert
    suspend fun insertAll(data : MutableList<DataX>)


    @Query("DELETE FROM datax")
    suspend fun deleteAll()

    @Update
    suspend fun updateAll(data : MutableList<DataX>)
}