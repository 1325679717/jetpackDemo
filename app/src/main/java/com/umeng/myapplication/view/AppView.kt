package com.umeng.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.umeng.myapplication.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
@SuppressLint("AppCompatCustomView")
class AppView(context: Context, attributeSet: AttributeSet) : TextView(context,attributeSet) {

//    @Inject
//    lateinit var adapter : ArticleAdapter
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
//        text = adapter.toString()
//        Log.d("AppView","adapter = $adapter")
    }
}