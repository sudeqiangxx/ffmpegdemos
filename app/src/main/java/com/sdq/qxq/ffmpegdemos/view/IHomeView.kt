package com.sdq.qxq.ffmpegdemos.view

import com.sdq.qxq.ffmpegdemos.model.Banner

/**
 * @description:
 * @author: lenna
 * @date: 2019-05-22
 * @update: 2019-05-22
 * @version: 1.0
 */
interface IHomeView :IBaseView {
    fun onResponse(banners:ArrayList<Banner>)
}