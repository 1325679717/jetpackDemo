package com.jp.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

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