package com.sdq.qxq.ffmpegdemos

import android.app.Application
import com.sdq.qxq.ffmpegdemos.net.LogUtils
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.TbsDownloader

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
class WanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppInitService.start(this)
    }
}