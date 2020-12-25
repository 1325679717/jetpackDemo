package com.jp.myapplication.module

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.jp.myapplication.db.AppDatabase
import com.jp.myapplication.db.DataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(app:Application) : AppDatabase {
        Log.d("RoomModule","app = $app")
        return Room.databaseBuilder(
                app,
                AppDatabase::class.java, "room_jetpack_db"
            ).build()

    }
    @Singleton
    @Provides
    fun provideDataDao(appDatabase: AppDatabase) : DataDao {
        return appDatabase.dataDao()
    }
}