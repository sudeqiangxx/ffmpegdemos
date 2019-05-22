package com.sdq.qxq.ffmpegdemos.wanandroid

import android.content.Context
import android.graphics.PixelFormat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.sdq.qxq.ffmpegdemos.R
import com.sdq.qxq.ffmpegdemos.wanandroid.activutil.ActivityUtil
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*
import java.lang.ref.WeakReference

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var url = intent.extras.getString(IExtras.WEB_URL_KEY)
        webview.loadUrl(url)
    }

}
