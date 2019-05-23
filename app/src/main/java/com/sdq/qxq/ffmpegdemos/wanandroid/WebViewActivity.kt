package com.sdq.qxq.ffmpegdemos.wanandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sdq.qxq.ffmpegdemos.R
import com.sdq.qxq.ffmpegdemos.base.BaseActivity
import com.sdq.qxq.ffmpegdemos.presenter.BasePresenter
import com.sdq.qxq.ffmpegdemos.view.IBaseView
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {
    override fun onCratePresenter(): BasePresenter<IBaseView>? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var url = intent.extras.getString(IExtras.WEB_URL_KEY)
        webview.loadUrl(url)
    }

}
