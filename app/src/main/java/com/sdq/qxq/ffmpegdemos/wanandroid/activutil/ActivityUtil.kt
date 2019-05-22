package com.sdq.qxq.ffmpegdemos.wanandroid.activutil

import android.app.Activity
import android.content.Intent
import com.sdq.qxq.ffmpegdemos.wanandroid.IExtras
import com.sdq.qxq.ffmpegdemos.wanandroid.WebViewActivity

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
object ActivityUtil {
    fun startWebActivity(activity:Activity,url:String){
        var intent=Intent(activity,WebViewActivity::class.java)
        intent.putExtra(IExtras.WEB_URL_KEY,url)
        activity.startActivity(intent)

    }
}