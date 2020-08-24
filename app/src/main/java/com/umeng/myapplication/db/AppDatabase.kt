package com.umeng.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umeng.myapplication.bean.DataX

@Database(entities = arrayOf(DataX::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}
