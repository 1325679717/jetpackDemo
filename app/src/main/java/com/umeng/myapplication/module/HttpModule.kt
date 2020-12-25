package com.umeng.myapplication.module

import android.os.Build
import androidx.annotation.RequiresApi
import com.umeng.myapplication.http.interceptor.HttpLogger
import com.umeng.myapplication.http.interceptor.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)//SingletonComponent
object HttpModule {
    const val BASE_URL = "https://www.wanandroid.com/"
    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient) : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideOkhttp() : OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger())
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
                           .connectTimeout(Duration.ofMillis(30000))
                           .readTimeout(Duration.ofMillis(30000))
                           .addInterceptor(RequestInterceptor())
                           .addInterceptor(httpLoggingInterceptor)
                           .build()
    }
}